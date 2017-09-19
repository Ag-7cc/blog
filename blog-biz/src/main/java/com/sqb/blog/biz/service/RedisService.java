package com.sqb.blog.biz.service;

import com.sqb.blog.dal.dao.RedisDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by vic
 * Create time : 2017/9/19 14:40
 */
@Service
public class RedisService {

    private static final Logger log = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    private RedisDao redisDao;


    public <T> Object getCacheValue(String key, Class<T> clazz) {
        Object val = redisDao.get(key);
        if (val instanceof Map) {
            return convertMap((Map) val, clazz);
        }
        if (val instanceof List) {
            return convertMapList((List<Map>) val, clazz);
        }
        return val;
    }

    public boolean addCache(String key, Object value, long expire) {
        redisDao.set(key, value, expire);
        return true;
    }

    public boolean addCache(String key, Object value) {
        redisDao.set(key, value);
        return true;
    }

    public boolean deleteCache(String key) {
        return redisDao.delete(key);
    }

    public boolean deleteRawCacheList(Set<String> keys) {
        return redisDao.delete(keys);
    }

    private <T> T convertMap(Map map, Class<T> type) {
        Object obj = null;
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(type);
            obj = type.newInstance();
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (!map.containsKey(propertyName)) {
                    continue;
                }
                try {
                    Object value = map.get(propertyName);
                    Object[] args = new Object[1];
                    args[0] = value;
                    descriptor.getWriteMethod().invoke(obj, args);
                } catch (InvocationTargetException e) {
                    log.error("error", e);
                } catch (IllegalAccessException e) {
                    log.error("error", e);
                }
            }
        } catch (Exception e) {
            log.error("error", e);
        }
        return obj == null ? null : (T) obj;
    }

    private <T> List<T> convertMapList(List<Map> list, Class<T> type) {
        if (CollectionUtils.isEmpty(list) || type == null) {
            return null;
        }
        List<T> result = new ArrayList<T>();
        list.forEach(l ->
                result.add(convertMap(l, type))
        );
        return result;
    }


}
