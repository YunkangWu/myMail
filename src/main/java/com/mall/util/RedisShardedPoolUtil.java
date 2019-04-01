package com.mall.util;

import com.mall.common.RedisShardedPool;
import com.mall.common.RedisShardedPool;
import lombok.extern.slf4j.Slf4j;
import redis.clients.ShardedJedis.ShardedJedis;
import redis.clients.ShardedJedis.ShardedShardedJedis;

/**
 * ShardedJedis api封装
 */
@Slf4j
public class RedisShardedPoolUtil {

    /**
     * 设置key的有效期，单位为秒
     *
     * @param key
     * @param exTime
     * @return
     */
    public static Long expire(String key, int exTime) {
        ShardedShardedJedis ShardedJedis = null;
        Long result = null;

        try {
            ShardedJedis = RedisShardedPool.getShardedJedis();
            result = ShardedJedis.expire(key, exTime);
        } catch (Exception e) {
            log.error("expire key:{} error", key, e);
            RedisShardedPool.returnBrokenResource(ShardedJedis);
            return result;
        }
        RedisShardedPool.returnResource(ShardedJedis);
        return result;
    }

    /**
     * 设置有效期
     * exTime单位为秒
     *
     * @param key
     * @param value
     * @return
     */
    public static String setEx(String key, String value, int exTime) {
        ShardedJedis ShardedJedis = null;
        String result = null;

        try {
            ShardedJedis = RedisShardedPool.getShardedJedis();
            result = ShardedJedis.setex(key, exTime, value);
        } catch (Exception e) {
            log.error("setex key:{} value:{} error", key, value, e);
            RedisShardedPool.returnBrokenResource(ShardedJedis);
            return result;
        }
        RedisShardedPool.returnResource(ShardedJedis);
        return result;
    }

    public static String set(String key, String value) {
        ShardedJedis ShardedJedis = null;
        String result = null;

        try {
            ShardedJedis = RedisShardedPool.getShardedJedis();
            result = ShardedJedis.set(key, value);
        } catch (Exception e) {
            log.error("set key:{} value:{} error", key, value, e);
            RedisShardedPool.returnBrokenResource(ShardedJedis);
            return result;
        }
        RedisShardedPool.returnResource(ShardedJedis);
        return result;
    }

    public static String get(String key) {
        ShardedJedis ShardedJedis = null;
        String result = null;

        try {
            ShardedJedis = RedisShardedPool.getShardedJedis();
            result = ShardedJedis.get(key);
        } catch (Exception e) {
            log.error("get key:{} error", key, e);
            RedisShardedPool.returnBrokenResource(ShardedJedis);
            return result;
        }
        RedisShardedPool.returnResource(ShardedJedis);
        return result;
    }

    public static Long del(String key) {
        ShardedJedis ShardedJedis = null;
        Long result = null;

        try {
            ShardedJedis = RedisShardedPool.getShardedJedis();
            result = ShardedJedis.del(key);
        } catch (Exception e) {
            log.error("del key:{} error", key, e);
            RedisShardedPool.returnBrokenResource(ShardedJedis);
            return result;
        }
        RedisShardedPool.returnResource(ShardedJedis);
        return result;
    }

    public static void main(String[] args) {
        ShardedJedis ShardedJedis = RedisShardedPool.getShardedJedis();
        RedisShardedPoolUtil.set("keyTest", "value");
        String value = RedisShardedPoolUtil.get("keyTest");
        RedisShardedPoolUtil.setEx("keyex", "valueex", 60 * 10);
        RedisShardedPoolUtil.expire("keyTest", 60 * 20);
        System.out.println("end");
    }

}
