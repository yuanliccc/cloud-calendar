package com.yl.jedis;

/**
 * 工厂接口
 * @param <T> 工厂产物类型
 * @author YuanLi
 */
public interface Factory<T> {

    T create();

}
