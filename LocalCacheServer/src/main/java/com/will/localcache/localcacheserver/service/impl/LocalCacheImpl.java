package com.will.localcache.localcacheserver.service.impl;

import com.will.localcache.localcacheserver.service.LocalCache;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用currenthashmap实现缓存服务
 * @author willWang
 * @since 2022/2/21
 */
@Service
public class LocalCacheImpl implements LocalCache, Serializable {


    private static final long serialVersionUID = 1L;

    /**
     * 默认有效时长,单位:秒
     */
    private static final int DEFUALT_TIMEOUT = 3600;

    private static final long SECOND_TIME = 1000;

    private static final Map<String, Object> map;

    private static final Timer timer;

    /**
     * 初始化
     */
    static {
        timer = new Timer();
        map = new ConcurrentHashMap<>();
    }

    /**
     * 私有构造函数,工具类不允许实例化
     */
    private LocalCacheImpl() {

    }

    /**
     * 清除缓存任务类
     */
    static class CleanWorkerTask extends TimerTask {

        private String key;

        public CleanWorkerTask(String key) {
            this.key = key;
        }

        public void run() {
            LocalCacheImpl.privteRemove(key);
        }
    }

    /**
     * 增加缓存
     *
     * @param key
     * @param value
     */
    @Override
    public void put(String key, Object value) {
        map.put(key, value);
        timer.schedule(new CleanWorkerTask(key), DEFUALT_TIMEOUT);
    }


    /**
     * 增加缓存
     *
     * @param key
     * @param value
     * @param timeout 有效时长
     */
    @Override
    public void put(String key, Object value, int timeout) {
        map.put(key, value);
        timer.schedule(new CleanWorkerTask(key), timeout * SECOND_TIME);
    }

    /**
     * 增加缓存
     *
     * @param key
     * @param value
     * @param expireTime 过期时间
     */
    @Override
    public void put(String key, Object value, Date expireTime) {
        map.put(key, value);
        timer.schedule(new CleanWorkerTask(key), expireTime);
    }


    /**
     * 批量增加缓存
     *
     * @param m
     */
    @Override
    public void putAll(Map<String, Object> m) {
        map.putAll(m);

        for (String key : m.keySet()) {
            timer.schedule(new CleanWorkerTask(key), DEFUALT_TIMEOUT);
        }
    }

    /**
     * 批量增加缓存
     *
     * @param m
     */
    @Override
    public void putAll(Map<String, Object> m, int timeout) {
        map.putAll(m);

        for (String key : m.keySet()) {
            timer.schedule(new CleanWorkerTask(key), timeout * SECOND_TIME);
        }
    }

    /**
     * 批量增加缓存
     *
     * @param m
     */
    @Override
    public void putAll(Map<String, Object> m, Date expireTime) {
        map.putAll(m);

        for (String key : m.keySet()) {
            timer.schedule(new CleanWorkerTask(key), expireTime);
        }
    }

    /**
     * 获取缓存
     *
     * @param key
     * @return
     */
    @Override
    public Object get(String key) {
        return map.get(key);
    }

    /**
     * 查询缓存是否包含key
     *
     * @param key
     * @return
     */
    @Override
    public boolean containsKey(String key) {
        return map.containsKey(key);
    }

    /**
     * 删除缓存
     *
     * @param key
     */
    @Override
    public void remove(String key) {
        map.remove(key);
    }

    private static void privteRemove(String key) {
        map.remove(key);
    }

    /**
     * 删除缓存
     *
     * @param o
     */
    @Override
    public void remove(Object o) {
        map.remove(o);
    }

    /**
     * 返回缓存大小
     *
     * @return
     */
    @Override
    public int size() {
        return map.size();
    }

    /**
     * 获取所有缓存数据
     *
     * @return Map
     */
    @Override
    public Map<String, Object> getAllCacheData(){
        return map;
    }

    /**
     * 清除所有缓存
     *
     * @return
     */
    @Override
    public void clear() {
        if (size() > 0) {
            map.clear();
        }
        timer.cancel();
    }

}
