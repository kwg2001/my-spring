package com.kwg.springframework.beans.factory.config;

import com.kwg.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * @Auther: kwg2001
 * @Date: 2022/5/5 14:56
 * @Description:  BeanFactory 的配置化接口
 *
 * 大多数bean工厂要实现的配置接口。除了 {@link springframework.beans.factory.BeanFactory}
 * 接口中的 bean 工厂客户端方法之外，还提供了配置 bean 工厂的工具。
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory,SingletonBeanRegistry {

    /**
     * bean 的scope属性，先之只提供这两个
     */
    final String SCOPE_SINGLETON="singleton";
    final String SCOPE_PROTOTYPE="prototype";

    /**
     * 增加 修改bean 的功能
     * @param beanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);


}
