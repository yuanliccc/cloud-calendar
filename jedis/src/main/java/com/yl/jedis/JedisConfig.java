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
     * 最大连接数 {@link #maxTotal}
     */
    public static final String MAX_TOTAL_KEY = "redis.pool.maxTotal";

    private int maxTotal;

    /**
     * 最大空闲连接数 {@link #maxIdle}
     */
    public static final String MAX_IDLE_KEY = "redis.pool.maxIdle";

    private int maxIdle;

    /**
     * 最小空闲连接数 {@link #minIdle}
     */
    public static final String MIN_IDLE_KEY = "redis.pool.minIdle";

    private int minIdle;

    /**
     * 当无连接可用时，是否允许阻塞等待，设置等待时间 {@code MAX_WAIT_MILLIS_KEY} {@link #blockedWhenExhausted}
     */
    public static final String BLOCKED_WHEN_EXHAUSTED_KEY = "redis.pool.blockedWhenExhausted";

    private long blockedWhenExhausted;

    /**
     * 最大等待时间 {@link #maxWaitMillis}
     */
    public static final String MAX_WAIT_MILLIS_KEY = "redis.pool.maxWaitMillis";

    private long maxWaitMillis;

    /**
     * 获取连接时，是否进行连接有效性测试，检测会进行 ping 操作, true or false {@link #testOnBorrow}
     */
    public static final String TEST_ON_BORROW = "redis.pool.testOnBorrow";

    private boolean testOnBorrow;

    /**
     * 释放连接时，是否进行连接有效性测试，检测会进行 ping 操作 {@link #testOnReturn}
     */
    public static final String TEST_ON_RETURN_KEY = "redis.pool.testOnReturn";

    private boolean testOnReturn;

    /**
     * 是否开启 jmx 监控 {@link #jmxEnable}
     */
    public static final String JMX_ENABLE_KEY = "redis.pool.jmxEnable";

    private boolean jmxEnable;

    /**
     * 是否开启空闲检测，默认 false {@link #testWhileIdle}
     */
    public static final String TEST_WHILE_IDLE_KEY = "redis.pool.testWhileIdle";

    private boolean testWhileIdle;

    /**
     * 空闲资源的检测周期，单位 millis，默认 -1 -> 不检测 {@link #timeBetweenEvictionRunsMillis}
     */
    public static final String TIME_BETWEEN_EVICTION_RUNS_MILLIS_KEY = "redis.pool.timeBetweenEvictionRunsMillis";

    private long timeBetweenEvictionRunsMillis;

    /**
     * 空闲资源最小存活时间，达到时间后将会被移除，单位 millis {@link #minEvictableIdleTimeMillis}
     */
    public static final String MIN_EVICTABLE_IDLE_TIME_MILLIS_KEY = "redis.pool.minEvictableIdleTimeMillis";

    private long minEvictableIdleTimeMillis;

    /**
     * 空闲资源检测时的采样数 {@link #numTestsPerEvictionRun}
     */
    public static final String NUM_TESTS_PER_EVICTION_RUN_KEY = "redis.pool.numTestsPerEvictionRun";

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

    public long getBlockedWhenExhausted() {
        return blockedWhenExhausted;
    }

    public void setBlockedWhenExhausted(long blockedWhenExhausted) {
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
