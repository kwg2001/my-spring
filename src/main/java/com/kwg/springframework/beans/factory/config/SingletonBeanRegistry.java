package com.kwg.springframework.beans.factory.config;

/**
 * @Auther: kwg2001
 * @Date: 2022/5/2 10:36
 * @Description:单例注册接口，能够让实现类具有单例注册属性
 */
public interface SingletonBeanRegistry {
    /**
     * 获取单例bean
     */
    public Object getSingleton(String name);

}
