package com.kwg.springframework.test.common;/**
 * @Auther: kwg2001
 * @Date: 2022/5/8 14:21
 * @Description:
 */

import com.kwg.springframework.beans.BeansException;
import com.kwg.springframework.beans.PropertyValue;
import com.kwg.springframework.beans.PropertyValues;
import com.kwg.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.kwg.springframework.beans.factory.config.BeanDefinition;
import com.kwg.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @program: my-spring
 *
 * @description:
 *
 * @author: Kwg
 *
 * @create: 2022-05-08 14:21
 **/
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition beanDefinition=beanFactory.getBeanDefinition("userService");

        PropertyValues propertyValues=beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company","改为：字节跳动"));
    }
}
