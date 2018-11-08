package com.yl.jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * Jedis 配置
 *
 * @author YuanLi
 */
public class JedisConfig {

    public static final String SERVERS_KEY = "redis.servers";

    public static final String PASSWORDS_KEY = "redis.passwords";

    public static final String MAX_TOTAL_KEY = "redis.pool.maxTotal";

    public static final String MAX_IDLE_KEY = "redis.pool.maxIdle";

    public static final String MIN_IDLE_KEY = "redis.pool.minIdle";

    public static final String MAX_WAIT_MILLIS_KEY = "redis.pool.maxWaitMillis";

    public static final String TEST_ON_BORROW = "redis.pool.testOnBorrow";

    public static final String TEST_ON_RETURN_KEY = "redis.pool.testOnReturn";

    private Map<String, String> properties;

    public JedisConfig() {

        properties = new HashMap<>();
    }

    public void setServers(String servers) {

    }

}
