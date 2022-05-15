package com.kwg.springframework.beans.factory.config;

/**
 * @Auther: kwg2001
 * @Date: 2022/5/5 15:10
 * @Description: 此接口用于修改 实例化 Bean 对象 。
 *
 * 允许自定义修改新 bean 实例的工厂钩子，
 * 例如检查标记接口或用代理包装它们。
 * Factory hook that allows for custom modification of new bean instances,
 *  * e.g. checking for marker interfaces or wrapping them with proxies.
 */
public interface BeanPostProcessor {

    /**
     * 在bean对象实例化之前调用此方法，对bean进行修改
     * @param bean
     * @param beanName
     * @return
     */
    Object postProcessBeforeInitialization(Object bean,String beanName);

    /**
     * 在bean 对象实例化之后，调用此方法，实现对bean的修改
     * @param bean
     * @param beanName
     * @return
     */
    Object postProcessAfterInitialization(Object bean,String beanName);
}
