package com.yl.jedis;

import redis.clients.jedis.JedisPool;

public class JedisPoolFactory implements Factory<JedisPool>{

    @Override
    public JedisPool create() {
        return null;
    }
}
