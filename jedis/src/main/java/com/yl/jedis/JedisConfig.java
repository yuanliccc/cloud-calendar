package com.yl.jedis;

/**
 * Jedis 配置
 *
 * @author YuanLi
 */
public class JedisConfig {

    /**
     * Redis 服务器 ip + 端口，逗号分隔
     * {@link #servers}
     */
    public static final String SERVERS_KEY = "redis.servers";

    private String[] servers;

    /**
     * Redis 服务器密码，逗号分隔 {@link #passwords}
     */
    public static final String PASSWORDS_KEY = "redis.passwords";

    private String[] passwords;

    /**
     * 最大连接数 {@link #maxTotal}，默认值 ： 8
     */
    public static final String MAX_TOTAL_KEY = "redis.pool.maxTotal";

    public static final int DEFAULT_MAX_TOTAL = 8;

    private int maxTotal;

    /**
     * 最大空闲连接数 {@link #maxIdle}，默认值 8
     */
    public static final String MAX_IDLE_KEY = "redis.pool.maxIdle";

    public static final int DEFAULT_MAX_IDLE = 8;

    private int maxIdle;

    /**
     * 最小空闲连接数 {@link #minIdle}，默认值 0
     */
    public static final String MIN_IDLE_KEY = "redis.pool.minIdle";

    public static final int DEFAULT_MIN_IDLE = 0;

    private int minIdle;

    /**
     * 当无连接可用时，是否允许阻塞等待，设置等待时间 {@code MAX_WAIT_MILLIS_KEY} {@link #blockedWhenExhausted}，
     * 默认值 true
     */
    public static final String BLOCKED_WHEN_EXHAUSTED_KEY = "redis.pool.blockedWhenExhausted";

    public static final boolean DEFAULT_BLOCKED_WHEN_EXHAUSTED = true;

    private boolean blockedWhenExhausted;

    /**
     * 最大等待时间 {@link #maxWaitMillis}，默认值 -1，表示永远不超时
     */
    public static final String MAX_WAIT_MILLIS_KEY = "redis.pool.maxWaitMillis";

    public static final long DEFAULT_MAX_WAIT_MILLIS = -1;

    private long maxWaitMillis;

    /**
     * 获取连接时，是否进行连接有效性测试，检测会进行 ping 操作, true or false {@link #testOnBorrow}，默认值 false
     */
    public static final String TEST_ON_BORROW_KEY = "redis.pool.testOnBorrow";

    public static final boolean DEFAULT_TEST_ON_BORROW = false;

    private boolean testOnBorrow;

    /**
     * 释放连接时，是否进行连接有效性测试，检测会进行 ping 操作 {@link #testOnReturn}，默认值 false
     */
    public static final String TEST_ON_RETURN_KEY = "redis.pool.testOnReturn";

    public static final boolean DEFAULT_TEST_ON_RETURN = false;

    private boolean testOnReturn;

    /**
     * 是否开启 jmx 监控 {@link #jmxEnable}，默认值 true
     */
    public static final String JMX_ENABLE_KEY = "redis.pool.jmxEnable";

    public static final boolean DEFAULT_JMX_ENABLE = true;

    private boolean jmxEnable;

    /**
     * 是否开启空闲检测，默认 false {@link #testWhileIdle}，默认值 false
     */
    public static final String TEST_WHILE_IDLE_KEY = "redis.pool.testWhileIdle";

    public static final boolean DEFAULT_TEST_WHILE_IDLE = false;

    private boolean testWhileIdle;

    /**
     * 空闲资源的检测周期，单位 millis {@link #timeBetweenEvictionRunsMillis}，默认值 30000
     */
    public static final String TIME_BETWEEN_EVICTION_RUNS_MILLIS_KEY
            = "redis.pool.timeBetweenEvictionRunsMillis";

    public static final long DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS = 30000;

    private long timeBetweenEvictionRunsMillis;

    /**
     * 空闲资源最小存活时间，达到时间后将会被移除，单位 millis {@link #minEvictableIdleTimeMillis}，默认 60000
     */
    public static final String MIN_EVICTABLE_IDLE_TIME_MILLIS_KEY = "redis.pool.minEvictableIdleTimeMillis";

    public static final long DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS = 60000;

    private long minEvictableIdleTimeMillis;

    /**
     * 空闲资源检测时的采样数 {@link #numTestsPerEvictionRun}，默认值 3
     */
    public static final String NUM_TESTS_PER_EVICTION_RUN_KEY = "redis.pool.numTestsPerEvictionRun";

    public static final int DEFAULT_NUM_TESTS_PER_EVICTION_RUN = 3;

    private int numTestsPerEvictionRun;


    public JedisConfig() {
    }

    public String[] getServers() {
        return servers;
    }

    public void setServers(String[] servers) {
        this.servers = servers;
    }

    public String[] getPasswords() {
        return passwords;
    }

    public void setPasswords(String[] passwords) {
        this.passwords = passwords;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public boolean getBlockedWhenExhausted() {
        return blockedWhenExhausted;
    }

    public void setBlockedWhenExhausted(boolean blockedWhenExhausted) {
        this.blockedWhenExhausted = blockedWhenExhausted;
    }

    public long getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(long maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public boolean isJmxEnable() {
        return jmxEnable;
    }

    public void setJmxEnable(boolean jmxEnable) {
        this.jmxEnable = jmxEnable;
    }

    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public long getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public long getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public int getNumTestsPerEvictionRun() {
        return numTestsPerEvictionRun;
    }

    public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }
}
