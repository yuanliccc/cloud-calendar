package com.yl.jedis;

import com.yl.common.util.PropertiesReaderUtil;

import java.util.Properties;

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



        return null;
    }
}