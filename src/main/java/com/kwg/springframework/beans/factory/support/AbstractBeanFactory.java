package com.kwg.springframework.beans.factory.support;/**
 * @Auther: kwg2001
 * @Date: 2022/5/2 10:15
 * @Description:
 */

import com.kwg.springframework.beans.factory.BeanFactory;
import com.kwg.springframework.beans.BeansException;
import com.kwg.springframework.beans.factory.config.BeanDefinition;
import com.kwg.springframework.beans.factory.config.BeanPostProcessor;
import com.kwg.springframework.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: my-spring
 *
 * @description:
 *
 * @author: Kwg
 *
 * @create: 2022-05-02 10:15
 **/
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    /**
     * 在 createBean 中应用的 BeanPostProcessors
     */
    private final List<BeanPostProcessor> beanPostProcessors=new ArrayList<BeanPostProcessor>();


    /**
     *
     * 模版方法，如果在getbean的时候，这个bean没有被实例化在singletonBeanMap中。
     *  那么就在beanDefinition中获取这个bean的beanDefinition
     *  然后进行实例化。
     *
     * @param name
     * @return
     */
    @Override
    public Object getBean(String name) {
//        Object bean=getSingleton(name);
//        if(bean!=null){
//            return bean;
//        }else {
//            BeanDefinition beanDefinition=getBeanDefinition(name);
//            return createBean(name,beanDefinition);
//
//        }
        return doGetBean(name,null);

    }

    @Override
    public Object getBean(String name, Object... args) {
        return doGetBean(name,args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        //强转 一下子
        return (T) getBean(name);
    }


    protected <T> T doGetBean(final String name,final Object[] args){
        Object bean=getSingleton(name);
        if(bean==null){
            BeanDefinition beanDefinition=getBeanDefinition(name);
            return (T) createBean(name,beanDefinition,args);
        }
        return (T) bean;
    }

    /**
     * 模版模式
     * 定义抽象方法，让其他功能的类实现此方法
     */
    public abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName,BeanDefinition beanDefinition,Object[] args) throws BeansException;


    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        //如果存在的话，先删除在 添加
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors(){
        return this.beanPostProcessors;
    }
}
