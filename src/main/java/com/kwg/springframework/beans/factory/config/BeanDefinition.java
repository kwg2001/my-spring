package com.kwg.springframework.beans.factory.config;/**
 * @Auther: kwg2001
 * @Date: 2022/5/1 23:01
 * @Description:
 */

/**
 * @program: my-spring
 *
 * @description: Bean实例化信息类
 *
 * @author: Kwg
 *
 * @create: 2022-05-01 23:01
 **/
public class BeanDefinition {
    /**
     * 通过Class记录bean的类信息
     * 优点：object的话需要自己手动new实例
     */
    private Class beanClass;

    public BeanDefinition(Class beanClass){
        this.beanClass=beanClass;
    }

    public Class getBeanClass(){
        return beanClass;
    }
    public void setBeanClass(Class beanClass){
        this.beanClass=beanClass;
    }

}
