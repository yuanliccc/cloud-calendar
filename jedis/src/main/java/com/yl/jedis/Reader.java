package com.yl.jedis;

/**
 * 读取器
 * @param <T> 返回类型
 */
public interface Reader<T> {

    T read();
}
