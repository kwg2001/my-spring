package com.kwg.springframework.beans.factory;

import com.kwg.springframework.beans.BeansException;
import com.kwg.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.kwg.springframework.beans.factory.config.BeanDefinition;
import com.kwg.springframework.beans.factory.config.BeanPostProcessor;
import com.kwg.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * @Auther: kwg2001
 * @Date: 2022/5/5 15:24
 * @Description:
 *
 * Configuration interface to be implemented by most listable bean factories.
 * In addition to {@link ConfigurableBeanFactory}, it provides facilities to
 * analyze and modify bean definitions, and to pre-instantiate singletons.
 *
 *  大多数可列出的bean工厂要实现的配置接口。除了 {@link ConfigurableBeanFactory} 之外，
 *  它还提供了分析和修改 bean 定义以及预实例化单例的工具。
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {


    void preInstantiateSingletons() throws BeansException;

    /**
     * 添加修改bean机制，继承自ConfigurableBeanFactory
     * @param beanPostProcessor
     */
    @Override
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
