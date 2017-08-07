package pers.tbsowa.redis.utils;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import pers.tbsowa.core.utils.JSONUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class IRedisUtil {
	
    private RedisTemplate<String, ?> redisTemplate;
    
	public IRedisUtil(RedisTemplate<String, ?> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public boolean set(final String key, final String value) {
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

	public String get(final String key) {
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

	/**
	 * expire 为给定 key 设置过期时间
	 * @param key
	 * @param expire
	 * @return
	 */
	public boolean expire(final String key, long expire) {
		return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
	}

	public <T> boolean setList(String key, List<T> list) {
		String value = JSONUtils.toJson(list);
		return set(key,value);
	}

	public <T> List<T> getList(String key, Class<T> clz) {
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
	public long lpush(final String key, Object obj) {
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
	public long rpush(final String key, Object obj) {
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
	public String lpop(final String key) {
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
