package com.kwg.springframework.Context.support;/**
 * @Auther: kwg2001
 * @Date: 2022/5/5 15:53
 * @Description:
 */

import com.kwg.springframework.Context.ConfigurableApplication;
import com.kwg.springframework.beans.BeansException;
import com.kwg.springframework.beans.factory.BeanFactory;
import com.kwg.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.kwg.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.kwg.springframework.beans.factory.config.BeanPostProcessor;
import com.kwg.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @program: my-spring
 *
 * @description: 应用向下文的抽象类
 *
 * @author: Kwg
 *
 * @create: 2022-05-05 15:53
 **/
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplication {
    /**
     * Spring 核心方法
     *  实现自ConfigurableApplication
     * @throws BeansException
     */
    @Override
    public void refresh() throws BeansException {

        //1、有就刷新，没有就创建beanFactory 加载BeanDefinition
        refershBeanFactory();

        //2、获取BeanFactory
        ConfigurableListableBeanFactory beanFactory=getBeanFactory();

        //3、针对特殊需求，在bean 实例化之前 执行BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);

        //4、如果有BeanPostProcessor  那么就需要提前将BeanProcessor实例化
        registerBeanPostProcessors(beanFactory);

        //5、提前实例化单例bean。恶汉式
        beanFactory.preInstantiateSingletons();
    }

    //有就刷新，没有就创建
    public abstract void refershBeanFactory() throws BeansException;

    public abstract ConfigurableListableBeanFactory getBeanFactory();

    public void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory){

        //获取BeanFactoryPostProcessors
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap= beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);

        //执行postProcessBeanFactory，对beanDefinition进行加强
        for(BeanFactoryPostProcessor beanFactoryPostProcessor:beanFactoryPostProcessorMap.values()){
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

        //如果有BeanPostProcessor  那么就需要提前将BeanProcessor实例化
    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String,BeanPostProcessor> beanPostProcessorMap =beanFactory.getBeansOfType(BeanPostProcessor.class);

        for(BeanPostProcessor beanPostProcessor:beanPostProcessorMap.values()){
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }


    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

}
