package pers.tbsowa.redis.utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;

import pers.tbsowa.core.utils.JSONUtils;

public class IRedisUtil {
	
    private static RedisTemplate<String, Object> redisTemplate;
    
	@SuppressWarnings("static-access")
	public IRedisUtil(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public static boolean set(final String key, final String value) {
		return redisTemplate.execute(new RedisCallback<Boolean>(){
			@Override
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				connection.set(serializer.serialize(key), serializer.serialize(value));
				return true;
			}
		});
	}
	
	public static boolean set(final String key, final String value, long timeout, TimeUnit unit) {
		ValueOperations<String, Object>  valueOperations = redisTemplate.opsForValue();
		valueOperations.set(key, value, timeout, unit);
		return true;
	}

	public static String get(final String key) {
		return redisTemplate.execute(new RedisCallback<String>(){
			@Override
			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] value = connection.get(serializer.serialize(key));
				return serializer.deserialize(value);
			}
		});
	}

	public static boolean delete(String key){
		redisTemplate.delete(key);
		return true;
	}
	
	/**
	 * expire 为给定 key 设置过期时间
	 * @param key
	 * @param expire
	 * @return
	 */
	public static boolean expire(final String key, long expire) {
		return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
	}

	public static <T> boolean setList(String key, List<T> list) {
		String value = JSONUtils.toJson(list);
		return set(key,value);
	}

	public static <T> List<T> getList(String key, Class<T> clz) {
		String json = get(key);
		if(json!=null){
			List<T> list = JSONUtils.toList(json, clz);
			return list;
		}
		return null;
	}

	/**
	 * 将一个或多个值插入到列表头部
	 * @param key
	 * @param obj
	 * @return
	 */
	public static long lpush(final String key, Object obj) {
		final String value = JSONUtils.toJson(obj);
		return redisTemplate.execute(new RedisCallback<Long>(){
			@Override
			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String>  serializer = redisTemplate.getStringSerializer();
				return connection.lPush(serializer.serialize(key), serializer.serialize(value));
			}
		});
	}

	/**
	 * 在列表中添加一个或多个值
	 * @param key
	 * @param obj
	 * @return
	 */
	public static long rpush(final String key, Object obj) {
		final String value = JSONUtils.toJson(obj);
		return redisTemplate.execute(new RedisCallback<Long>(){
			@Override
			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String>  serializer = redisTemplate.getStringSerializer();
				return connection.rPush(serializer.serialize(key), serializer.serialize(value));
			}
		});
	}

	/**
	 * 移出并获取列表的第一个元素
	 * @param key
	 * @return
	 */
	public static String lpop(final String key) {
		return redisTemplate.execute(new RedisCallback<String>(){
			@Override
			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String>  serializer = redisTemplate.getStringSerializer();
				byte[] value = connection.lPop(serializer.serialize(key));
				return serializer.deserialize(value);
			}
		});
	}

}
