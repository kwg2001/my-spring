package com.kwg.springframework.beans.factory.config;

import com.kwg.springframework.beans.BeansException;
import com.kwg.springframework.beans.factory.BeanFactory;

import java.beans.Beans;

/**
 * @Auther: kwg2001
 * @Date: 2022/5/5 15:29
 * @Description: 可以自动装配Bean的接口
 *
 * Extension of the {@link cn.bugstack.springframework.beans.factory.BeanFactory}
 * interface to be implemented by bean factories that are capable of
 * autowiring, provided that they want to expose this functionality for
 * existing bean instances.
 * {@link springframework.beans.factory.BeanFactory}
 * 接口的扩展，由能够自动装配的 bean 工厂实现，
 * 前提是他们希望为现有 bean 实例公开此功能
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessBeforeinitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsBeforeInitalization(Object existingBean,String beanName) throws BeansException;

    /**
     * 执行在BeanPostProcessors 接口的实现类中的postProcessorsAfterInitialization 方法
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean,String beanName) throws BeansException;


}
