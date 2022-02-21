package com.will.localcache.localcacheserver.controller;

import com.will.localcache.localcacheserver.service.LocalCache;
import com.will.localcache.localcacheserver.util.RespJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * TODO
 *
 * @author willWang
 * @since 2022/2/21
 */
@Api(value = "本地缓存服务", tags = "本地缓存服务")
@RestController
@RequestMapping("/localCacheServer")
public class LocaCacheController {

    @Resource
    private LocalCache localCache;

    /**
     * 增加缓存
     *
     * @param key
     * @param value
     */
    @ApiOperation("增加缓存")
    @PutMapping("/put")
    public RespJson<String> put(@RequestParam("key") String key,@RequestParam("value") Object value) {
        localCache.put(key, value);
        return RespJson.success("增加缓存成功");
    }


    /**
     * 增加缓存
     *
     * @param key
     * @param value
     * @param timeout 有效时长
     */
    @ApiOperation("增加缓存带有效时长")
    @PutMapping("/putWithTimeout")
    public RespJson<String> put(@RequestParam("key") String key,@RequestParam("value") Object value,@RequestParam("timeout") int timeout) {
        localCache.put(key, value, timeout);
        return RespJson.success("增加缓存成功");
    }

    /**
     * 增加缓存
     *
     * @param key
     * @param value
     * @param expireTime 过期时间
     */
    @ApiOperation("增加缓存带过期时间")
    @PutMapping("/putWithExpireTime")
    public RespJson<String> put(@RequestParam("key") String key,@RequestParam("value") Object value,@RequestParam("expireTime") Date expireTime) {
        localCache.put(key, value, expireTime);
        return RespJson.success("增加缓存成功");
    }


    /**
     * 批量增加缓存
     *
     * @param m
     */
    @ApiOperation("批量增加缓存")
    @PostMapping("/putAll")
    public RespJson<String> putAll(@RequestBody Map<String, Object> m) {
        localCache.putAll(m);
        return RespJson.success("批量增加缓存成功");
    }

    /**
     * 批量增加缓存
     *
     * @param m
     */
    @ApiOperation("批量增加缓存带有效时长")
    @PostMapping("/putAllWithTimeout")
    public RespJson<String> putAll(@RequestBody Map<String, Object> m, @RequestParam("timeout") int timeout) {
        localCache.putAll(m,timeout);
        return RespJson.success("批量增加缓存成功");
    }

    /**
     * 批量增加缓存
     *
     * @param m
     */
    @ApiOperation("批量增加缓存带过期时间")
    @PostMapping("/putAllWithExpireTime")
    public RespJson<String> putAll(@RequestBody Map<String, Object> m, @RequestParam("expireTime") Date expireTime) {
        localCache.putAll(m, expireTime);
        return RespJson.success("批量增加缓存成功");
    }

    /**
     * 获取缓存
     *
     * @param key
     * @return
     */
    @ApiOperation("获取缓存")
    @GetMapping("/get")
    public RespJson<Object> get(@RequestParam("key") String key) {
        return RespJson.success(localCache.get(key));
    }

    /**
     * 查询缓存是否包含key
     *
     * @param key
     * @return
     */
    @ApiOperation("查询缓存是否包含key")
    @GetMapping("/containsKey")
    public RespJson<Boolean> containsKey(@RequestParam("key") String key) {
        return RespJson.success(localCache.containsKey(key));
    }

    /**
     * 删除缓存
     *
     * @param key
     */
    @ApiOperation("删除缓存")
    @DeleteMapping("/remove")
    public RespJson<String> remove(@RequestParam("key") String key) {
        localCache.remove(key);
        return RespJson.success("删除缓存成功");
    }

    /**
     * 删除缓存
     *
     * @param o
     */
    @ApiOperation("删除缓存按Value")
    @PostMapping("/removeByValue")
    public RespJson<String> remove(@RequestBody Object o) {
        localCache.remove(o);
        return RespJson.success("删除缓存成功");
    }

    /**
     * 返回缓存大小
     *
     * @return
     */
    @ApiOperation("返回缓存大小")
    @GetMapping("/size")
    public RespJson<Integer> size() {
        return RespJson.success(localCache.size());
    }

    /**
     * 获取所有缓存数据
     *
     * @return Map
     */
    @ApiOperation("获取所有缓存数据")
    @GetMapping("/getAllCacheData")
    public RespJson<Map<String, Object>> getAllCacheData(){
        return RespJson.success(localCache.getAllCacheData());
    }

    /**
     * 清除所有缓存
     *
     * @return
     */
    @ApiOperation("清除所有缓存")
    @PutMapping("/clear")
    public RespJson<String> clear() {
        localCache.clear();
        return RespJson.success("删除缓存成功");
    }

}
