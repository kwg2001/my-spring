package com.kwg.springframework.beans.factory.support;/**
 * @Auther: kwg2001
 * @Date: 2022/5/2 10:15
 * @Description:
 */

import com.kwg.springframework.beans.factory.BeanFactory;
import com.kwg.springframework.beans.BeansException;
import com.kwg.springframework.beans.factory.config.BeanDefinition;

/**
 * @program: my-spring
 *
 * @description:
 *
 * @author: Kwg
 *
 * @create: 2022-05-02 10:15
 **/
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

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
}
