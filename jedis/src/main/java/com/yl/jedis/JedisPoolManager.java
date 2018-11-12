package com.yl.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Redis 连接池管理器 - 单例模式
 * @author YuanLi
 */
public class JedisPoolManager {

    private JedisPool jedisPool;

    private static class Internal {

        private static volatile transient JedisPoolManager jedisPoolManager
                = new JedisPoolManager();
    }

    private JedisPoolManager() {
        jedisPool = new JedisPoolFactory(JedisConfigReader.JEDIS_DEFAULT_CONFIG_FILE)
                .create();
    }

    public static JedisPoolManager getInstance() {
        return Internal.jedisPoolManager;
    }

    public Jedis getResource() {

        return jedisPool.getResource();
    }

    public void destroy() {

        jedisPool.destroy();
    }

    public void close() {
        jedisPool.close();
    }

}
