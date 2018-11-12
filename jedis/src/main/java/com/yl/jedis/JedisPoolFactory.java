package com.yl.jedis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.JedisPool;

/**
 * JedisPool 工厂
 * @author YuanLi
 */
class JedisPoolFactory implements Factory<JedisPool> {

    private String configFilePath;

    public JedisPoolFactory(String configFilePath) {

        this.configFilePath = configFilePath;
    }

    @Override
    public JedisPool create() {

        JedisConfig jedisConfig = new JedisConfigReader(this.configFilePath).read();
        GenericObjectPoolConfig config = new JedisConfigConverter().convert(jedisConfig);

        JedisPool jedisPool = new JedisPool(config, jedisConfig.getServer(),
                jedisConfig.getPort(), jedisConfig.getTimeout(),
                jedisConfig.getPassword());

        return jedisPool;
    }
}
