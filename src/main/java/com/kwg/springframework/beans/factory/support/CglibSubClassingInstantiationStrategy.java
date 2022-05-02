package com.kwg.springframework.beans.factory.support;/**
 * @Auther: kwg2001
 * @Date: 2022/5/2 16:11
 * @Description:
 */

import com.kwg.springframework.beans.BeansException;
import com.kwg.springframework.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * @program: my-spring
 *
 * @description:
 *
 * @author: Kwg
 *
 * @create: 2022-05-02 16:11
 **/
public class CglibSubClassingInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException {

        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public String toString() {
                return super.toString();
            }
        });
        if(null==constructor) {
            return enhancer.create();
        }
        return enhancer.create(constructor.getParameterTypes(),args);
    }
}
