package com.kwg.springframework.beans.factory.config;/**
 * @Auther: kwg2001
 * @Date: 2022/5/1 23:01
 * @Description:
 */

import com.kwg.springframework.beans.PropertyValues;

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

    /**
     * bean 的属性
     */
    private PropertyValues propertyValues;

    /**
     * 这两个initMethodName，destoryMethodName属性
     * 是为了在spring.xml 配置的Bean对象中的initmethod="initDataMethod" destroymethod="destroyDataMethod"
     * 服务的
     */
    private String initMethodName;

    private String destoryMethodName;




    /**
     * 不管有没有propertyValues参数，都要添加一个propertyValues对象
     * @param beanClass
     */
    public BeanDefinition(Class beanClass){
        this.beanClass=beanClass;
        this.propertyValues=new PropertyValues();
    }

    public BeanDefinition(Class beanClass,PropertyValues propertyValues){
        this.beanClass=beanClass;
        this.propertyValues=propertyValues!=null?propertyValues:new PropertyValues();
    }

    public Class getBeanClass(){
        return beanClass;
    }
    public void setBeanClass(Class beanClass){
        this.beanClass=beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestoryMethodName() {
        return destoryMethodName;
    }

    public void setDestoryMethodName(String destoryMethodName) {
        this.destoryMethodName = destoryMethodName;
    }
}
