package com.kwg.springframework.beans.factory.support;

import com.kwg.springframework.beans.BeansException;
import com.kwg.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @Auther: kwg2001
 * @Date: 2022/5/2 15:35
 * @Description:
 *      1、实例化策略接口
 *      2、两中实例化策略实现了这个接口：jdk和Cglib
 *      3、为带有构造参数的bean实例化服务
 */
public interface InstantiationStrategy {


    /**
     * 实例化带参数的bean
     * @param beanDefinition
     * @param beanName
     * @param constructor
     * @param args
     * @return
     * @throws BeansException
     */
    Object instantiate(BeanDefinition beanDefinition,
                       String beanName,
                       Constructor constructor,
                       Object[] args) throws BeansException;
}
