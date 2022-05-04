package com.kwg.springframework.beans.factory.support;/**
 * @Auther: kwg2001
 * @Date: 2022/5/2 14:08
 * @Description:
 */

import com.kwg.springframework.beans.BeansException;
import com.kwg.springframework.beans.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: my-spring
 *
 * @description: 具有注册beanDefinition能力的类
 *
 * @author: Kwg
 *
 * @create: 2022-05-02 14:08
 **/
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    /**
     * beanDefination的注册表
     */
    private Map<String,BeanDefinition> beanDefinitionMap=new ConcurrentHashMap<>();

    /**
     * 获取beanDefination
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition=beanDefinitionMap.get(beanName);
        if(beanDefinition==null){
            throw new BeansException("beanDefination of "+beanName+"is null");
        }
        return beanDefinition;
    }

    /**
     * 注册beanDefination
     * @param name
     * @param beanDefinition
     */
    @Override
    public void registBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name,beanDefinition);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }


}
