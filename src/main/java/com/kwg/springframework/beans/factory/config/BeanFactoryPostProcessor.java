package com.kwg.springframework.beans.factory.config;

import com.kwg.springframework.beans.BeansException;
import com.kwg.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * @Auther: kwg2001
 * @Date: 2022/5/4 17:10
 * @Description: 提供对beanDefinition修改的功能..允许在bean 注册了但没实例化之前修改BeanDefinition
 *                 在所有beanDefinition加载完成之后，实例化bean对象之前
 *                 函数式接口
 *
 * Allows for custom modification of
 * an application context's bean definitions,adapting the
 * bean property values of the context's underlying bean
 * factory.
 * 允许自定义修改应用程序上下文的 bean 定义，
 * 调整上下文底层 bean 工厂的 bean 属性值。
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有BeanDefinition加载完成之后，实例化bean对象之前，
     * 提供修改BeanDefinition 属性的机制
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
