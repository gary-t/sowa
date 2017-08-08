package pers.tbsowa.common.utils;


import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by DELL on 2017/8/8.
 */
public class BeanMapConvertUtils {

    private static final Log log = LogFactory.getLog(BeanMapConvertUtils.class);

    /**
     * javaBean转Map
     * @param object
     * @return
     * @throws Exception
     */
    public static Map<String, Object> beanToMap(Object object) {
        if(object == null){
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(object);
                    map.put(key, value);
                }

            }
        } catch (Exception e) {
           log.error(e);
        }

        return map;
    }

    /**
     * List javaBean 转List Map
     * @param beanList
     * @param <T>
     * @return
     */
    public static <T> List<Map<String, Object>> beanToMapInList(List<T> beanList) {
        return Lists.transform(beanList, new Function<T, Map<String, Object>>() {
            @Override
            public Map<String, Object> apply(T input) {
                return beanToMap(input);
            }
        });
    }

    /**
     * Map转javaBean
     * @param map
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) {
        T instance = null;
        try {
            instance = clazz.newInstance();
            BeanUtils.populate(instance, map);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            log.error(e);
        }
        return instance;
    }

    /**
     * List Map 转List javaBean
     * @param mapList
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> mapToBeanInList(List<Map<String, Object>> mapList, final Class<T> clazz) {
        return Lists.transform(mapList, new Function<Map<String, Object>, T>() {
            @Override
            public T apply(Map<String, Object> input) {
                return mapToBean(input, clazz);
            }
        });
    }
}
