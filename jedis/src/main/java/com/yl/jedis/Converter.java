package com.yl.jedis;

/**
 * 转换器
 * @param <T> 被转换对象
 * @param <R> 目标对象
 * @author YuanLi
 */
public interface Converter<T, R> {

    R convert(T converted);

}
