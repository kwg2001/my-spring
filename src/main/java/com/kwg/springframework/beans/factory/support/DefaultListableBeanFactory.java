package com.kwg.springframework.beans.factory.support;/**
 * @Auther: kwg2001
 * @Date: 2022/5/2 14:08
 * @Description:
 */

import com.kwg.springframework.beans.BeansException;
import com.kwg.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.kwg.springframework.beans.factory.config.BeanDefinition;
import com.kwg.springframework.beans.factory.config.BeanPostProcessor;

import java.util.HashMap;
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
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

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
            throw new BeansException("beanDefination of "+beanName+" is null");
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



    /**
     * 来自ListableBeanFactory中定义的方法。
     *  通过类型返回这个类的所有的BeandDefinition。。
     *  这里返回的Bean不是单例的那种。。。
     *  而是 返回的类似于BeanPostProcess，BeanFactoryPostProcess这样的。。。。
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {

        Map<String,T> result=new HashMap<>();
        beanDefinitionMap.forEach(
                (beanName,beanDefinition)->{
                    Class beanClass=beanDefinition.getBeanClass();
                    //isAssignableFrom : type是否为 beanClass 父类或本身
                    if(type.isAssignableFrom(beanClass)){
                        result.put(beanName,(T) getBean(beanName));
                    }
                }
        );
        return result;
    }


    /**
     * 对单例属性的Bean 通过饿汉式加载。
     * @throws BeansException
     */
    @Override
    public void preInstantiateSingletons() throws BeansException {
        beanDefinitionMap.keySet().forEach(this::getBean);

    }
}
