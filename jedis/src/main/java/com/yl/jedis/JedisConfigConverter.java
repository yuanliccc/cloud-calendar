package com.yl.jedis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * {@link JedisConfig} convert to {@link GenericObjectPoolConfig}
 * @author YuanLi
 */
public class JedisConfigConverter implements Converter<JedisConfig, GenericObjectPoolConfig> {

    @Override
    public GenericObjectPoolConfig convert(JedisConfig converted) {
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();

        genericObjectPoolConfig.setMaxIdle(converted.getMaxIdle());
        genericObjectPoolConfig.setMaxTotal(converted.getMaxTotal());
        genericObjectPoolConfig.setMinIdle(converted.getMinIdle());

        genericObjectPoolConfig.setBlockWhenExhausted(converted.getBlockedWhenExhausted());
        genericObjectPoolConfig.setMaxWaitMillis(converted.getMaxWaitMillis());

        genericObjectPoolConfig.setJmxEnabled(converted.isJmxEnable());

        genericObjectPoolConfig.setTestWhileIdle(converted.isTestWhileIdle());
        genericObjectPoolConfig.setTestOnBorrow(converted.isTestOnBorrow());
        genericObjectPoolConfig.setTestOnReturn(converted.isTestOnReturn());
        genericObjectPoolConfig.setNumTestsPerEvictionRun(converted.getNumTestsPerEvictionRun());
        genericObjectPoolConfig.setMinEvictableIdleTimeMillis(converted.getMinEvictableIdleTimeMillis());
        genericObjectPoolConfig.setTimeBetweenEvictionRunsMillis(converted.getTimeBetweenEvictionRunsMillis());

        return genericObjectPoolConfig;
    }
}
