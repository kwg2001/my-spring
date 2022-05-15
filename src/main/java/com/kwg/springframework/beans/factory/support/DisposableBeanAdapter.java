package com.kwg.springframework.beans.factory.support;/**
 * @Auther: kwg2001
 * @Date: 2022/5/8 17:03
 * @Description: 销毁方法的适配器类，因为销毁方法现在就有两个了，
 *                  但是在销毁执行时不希望还要关注有哪些销毁的方法类型，
 *                  更希望能有一个统一的接口进行销毁，
 *                  所以通过适配器来进行统一的处理。
 */

import cn.hutool.core.util.StrUtil;
import com.kwg.springframework.beans.BeansException;
import com.kwg.springframework.beans.factory.DisposableBean;
import com.kwg.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @program: my-spring
 *
 * @description:
 *
 * @author: Kwg
 *
 * @create: 2022-05-08 17:03
 **/
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;
    private final String beanName;
    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        //1、实现接口DisposableBean
        if(bean instanceof DisposableBean){
            ((DisposableBean) bean).destroy();
        }
        //2、配置信息的destory-methodName
        //  防止二次执行销毁方法
        //if(StrUtil.isNotEmpty(destroyMethodName)&&! (bean instanceof DisposableBean)&& "destroy".equals(this.destroyMethodName))
        if(StrUtil.isNotEmpty(destroyMethodName)&&! (bean instanceof DisposableBean)){
            Method destoryMethod =bean.getClass().getMethod(destroyMethodName);
            if(destoryMethod==null){
                throw new
                        BeansException(("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'"));

            }
            //执行destory方法
            destoryMethod.invoke(bean);
        }
    }
}
