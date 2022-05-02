package com.kwg.springframework.beans.factory.support;/**
 * @Auther: kwg2001
 * @Date: 2022/5/2 10:53
 * @Description:
 */

import com.kwg.springframework.beans.BeansException;
import com.kwg.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @program: my-spring
 *
 * @description:实例化bean的类
 *
 * @author: Kwg
 *
 * @create: 2022-05-02 10:53
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    /**
     * 通过Cglib方式，来创建bean实例
     */
    private InstantiationStrategy instantiationStrategy=new CglibSubClassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition,Object[] args) throws BeansException {
        Object bean ;
        try {
             bean = crateBeanInstance(beanName,beanDefinition,args);

        } catch (Exception e) {
            throw new  BeansException("Failed to instance of bean named "+beanName,e);
        }

        //bean 实例化好之后，将bean 放入单例对象的缓存中
        addSingleton(beanName,bean);
        return bean;
    }

    /**
     * 创建bean实例
     *
     */
    protected Object crateBeanInstance(String beanName,BeanDefinition beanDefinition,Object[] args){

        Constructor constructor=null;
        Class beanClass=beanDefinition.getBeanClass();
        Constructor[] declaredConstructors=beanClass.getDeclaredConstructors();
        for(Constructor ctor:declaredConstructors){
            if(args!=null && ctor.getParameterTypes().length==args.length ){
                constructor=ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition,beanName,constructor,args);
    }

    public InstantiationStrategy getInstantiationStrategy(){
        return instantiationStrategy;
    }




}