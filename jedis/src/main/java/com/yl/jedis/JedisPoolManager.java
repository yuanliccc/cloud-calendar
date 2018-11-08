package com.yl.jedis;


import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * Redis 连接池管理器 - 单例模式
 * @author YuanLi
 */
public class JedisPoolManager {

    private static volatile transient JedisPoolManager jedisPoolManager;

    private static Map<String, JedisPool> jedisPools;

    private JedisPoolManager() {}



}
