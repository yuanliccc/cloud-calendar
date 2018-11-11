package com.yl.jedis;

import com.yl.common.util.PropertiesReaderUtil;
import com.yl.common.util.StringUtil;

import java.util.Properties;
import static com.yl.jedis.JedisConfig.*;

/**
 * Jedis 配置文件读取器
 * @author YuanLi
 */
public class JedisConfigReader implements Reader<JedisConfig> {

    public static final String JEDIS_DEFAULT_CONFIG_FILE = "jedis-config.properties";

    private String jedisFilePath;

    public JedisConfigReader(String jedisFilePath) {

        if(jedisFilePath == null) {
            this.jedisFilePath = JEDIS_DEFAULT_CONFIG_FILE;
        }
        else {
            jedisFilePath = jedisFilePath.trim();

            if(jedisFilePath.equals("")) {
                this.jedisFilePath = JEDIS_DEFAULT_CONFIG_FILE;
            }
            else {
                this.jedisFilePath = jedisFilePath;
            }
        }

    }

    public JedisConfigReader() {
        this.jedisFilePath = JEDIS_DEFAULT_CONFIG_FILE;
    }

    @Override
    public JedisConfig read() {
        Properties properties = PropertiesReaderUtil.getProperties(this.jedisFilePath);
        JedisConfig jedisConfig = new JedisConfig();

        jedisConfig.setServers(StringUtil
                .nullToEmpty(properties.getProperty(SERVERS_KEY))
                .split(","));

        jedisConfig.setPasswords(StringUtil
                .nullToEmpty(properties.getProperty(PASSWORDS_KEY))
                .split(","));

        jedisConfig.setMaxTotal(nullToDefault(properties.get(MAX_TOTAL_KEY),
                DEFAULT_MAX_TOTAL));

        jedisConfig.setMaxIdle(nullToDefault(properties.get(MAX_IDLE_KEY),
                DEFAULT_MAX_IDLE));

        jedisConfig.setMinIdle(nullToDefault(properties.get(MIN_IDLE_KEY),
                DEFAULT_MIN_IDLE));

        jedisConfig.setBlockedWhenExhausted(nullToDefault(properties.get(BLOCKED_WHEN_EXHAUSTED_KEY),
                DEFAULT_BLOCKED_WHEN_EXHAUSTED));

        jedisConfig.setMaxWaitMillis(nullToDefault(properties.get(MAX_WAIT_MILLIS_KEY),
                DEFAULT_MAX_WAIT_MILLIS));

        jedisConfig.setTestOnBorrow(nullToDefault(properties.get(TEST_ON_BORROW_KEY),
                DEFAULT_TEST_ON_BORROW));

        jedisConfig.setTestOnReturn(nullToDefault(properties.get(TEST_ON_RETURN_KEY),
                DEFAULT_TEST_ON_RETURN));

        jedisConfig.setJmxEnable(nullToDefault(properties.get(JMX_ENABLE_KEY),
                DEFAULT_JMX_ENABLE));

        jedisConfig.setTestWhileIdle(nullToDefault(properties.get(TEST_WHILE_IDLE_KEY),
                DEFAULT_TEST_WHILE_IDLE));

        jedisConfig.setTimeBetweenEvictionRunsMillis(nullToDefault(properties.get(TIME_BETWEEN_EVICTION_RUNS_MILLIS_KEY),
                DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS));

        jedisConfig.setMinEvictableIdleTimeMillis(nullToDefault(properties.get(MIN_EVICTABLE_IDLE_TIME_MILLIS_KEY),
                DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS));

        jedisConfig.setNumTestsPerEvictionRun(nullToDefault(properties.get(NUM_TESTS_PER_EVICTION_RUN_KEY),
                DEFAULT_NUM_TESTS_PER_EVICTION_RUN));

        return jedisConfig;
    }

    private <T> T nullToDefault(Object aim, T defaultValue) {

        return aim == null ? defaultValue : (T)aim;
    }
}