package com.kwg.springframework.beans.factory.support;

import com.kwg.springframework.beans.factory.config.BeanDefinition;

/**
 * @Auther: kwg2001
 * @Date: 2022/5/2 14:03
 * @Description:
 *      注册beanDefinition的接口，实现此接口的类具有注册beanDefinition功能
 */
public interface BeanDefinitionRegistry {

    /**
     * 想注册表中注册beanDefinition
     * @param name
     * @param beanDefinition
     */
    public void registBeanDefinition(String name, BeanDefinition beanDefinition);

    /**
     * 判断是否包含这个BeanDefinition
     */
    boolean containsBeanDefinition(String beanName);
}
