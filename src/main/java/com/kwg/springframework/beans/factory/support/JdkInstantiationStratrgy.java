package com.kwg.springframework.beans.factory.support;/**
 * @Auther: kwg2001
 * @Date: 2022/5/2 15:44
 * @Description:
 */

import com.kwg.springframework.beans.BeansException;
import com.kwg.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @program: my-spring
 *
 * @description: Jdk实现的实例化策略
 *
 * @author: Kwg
 *
 * @create: 2022-05-02 15:44
 **/
public class JdkInstantiationStratrgy implements InstantiationStrategy{

    /**
     * 通过jdk反射，实现具有参数的构造函数的bean的实例化
     * @param beanDefinition
     * @param beanName
     * @param constructor
     * @param args
     * @return
     * @throws BeansException
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException{

        Class clazz=beanDefinition.getBeanClass();

        try {
            if(constructor!=null){
                return clazz.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
            }else {
                return clazz.getDeclaredConstructor().newInstance();
            }
        } catch (InvocationTargetException |InstantiationException |IllegalAccessException | NoSuchMethodException e) {
            throw new BeansException("Failed to instantiate "+clazz.getName(),e);
        }


    }
}
