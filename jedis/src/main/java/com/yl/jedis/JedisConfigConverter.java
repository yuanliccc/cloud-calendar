package com.yl.jedis;

import redis.clients.jedis.JedisPoolConfig;

public class JedisConfigConverter implements Converter<JedisConfig, JedisPoolConfig> {

    @Override
    public JedisPoolConfig convert(JedisConfig converted) {
        return null;
    }
}
