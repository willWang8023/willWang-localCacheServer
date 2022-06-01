package com.will.localcache.localcacheserver.service;

import java.util.Date;
import java.util.Map;

/**
 * @author wangwy
 * @since 2022/2/21
 */
public interface LocalCache {

    /**
     * 增加缓存
     *
     * @param key
     * @param value
     */
    void put(String key, Object value);


    /**
     * 增加缓存
     *
     * @param key
     * @param value
     * @param timeout 有效时长
     */
     void put(String key, Object value, int timeout);

    /**
     * 增加缓存
     *
     * @param key
     * @param value
     * @param expireTime 过期时间
     */
    void put(String key, Object value, Date expireTime);


    /**
     * 批量增加缓存
     *
     * @param m
     */
    void putAll(Map<String, Object> m);

    /**
     * 批量增加缓存
     *
     * @param m
     */
    void putAll(Map<String, Object> m, int timeout);

    /**
     * 批量增加缓存
     *
     * @param m
     */
    void putAll(Map<String, Object> m, Date expireTime);
    /**
     * 获取缓存
     *
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 查询缓存是否包含key
     *
     * @param key
     * @return
     */
    boolean containsKey(String key);

    /**
     * 删除缓存
     *
     * @param key
     */
    void remove(String key);

    /**
     * 删除缓存
     *
     * @param o
     */
    void remove(Object o);

    /**
     * 返回缓存大小
     *
     * @return
     */
    int size();

    /**
     * 获取所有缓存数据
     *
     * @return Map
     */
    Map<String, Object> getAllCacheData();

    /**
     * 清除所有缓存
     *
     * @return
     */
    void clear();

}
