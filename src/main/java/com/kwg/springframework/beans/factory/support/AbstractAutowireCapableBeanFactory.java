package com.kwg.springframework.beans.factory.support;/**
 * @Auther: kwg2001
 * @Date: 2022/5/2 10:53
 * @Description:
 */

import cn.hutool.core.bean.BeanUtil;
import com.kwg.springframework.beans.BeansException;
import com.kwg.springframework.beans.PropertyValue;
import com.kwg.springframework.beans.PropertyValues;
import com.kwg.springframework.beans.factory.config.BeanDefinition;
import com.kwg.springframework.beans.factory.config.BeanReference;

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
        Object bean=null ;
        try {
             bean = crateBeanInstance(beanName,beanDefinition,args);
             //bean创建好后，给bean填充属性。
            applyPropertyValues(beanName,bean,beanDefinition);
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

    /**
     * bean 的属性填充，
     *   属性在设置的时候，被设置成BeanReference类型
     *
     * @param beanName 自己的名字
     * @param bean ：bean本身
     * @param beanDefinition ：自己的定义包括需要注入的属性
     */
    protected void applyPropertyValues(String beanName,Object bean,BeanDefinition beanDefinition){
        try {
            PropertyValues propertyValues=beanDefinition.getPropertyValues();
            for(PropertyValue pv:propertyValues.getPropertyValues()){
                String propertyName=pv.getName();
                Object value=pv.getValue();
                //如果属性属于引用类型
                if(value instanceof BeanReference){

                    //当前的value属于bean的引用，包含bean的name
                    BeanReference beanReference=(BeanReference) value;
                    //这里的value就是获取属性的实例
                    value=getBean(beanReference.getBeanName());
                }
                //调用BeanUtil进行属性填充
                BeanUtil.setFieldValue(bean,propertyName,value);
            }
        } catch (Exception e){
            throw new BeansException("Error setting property values:"+beanName);
        }


    }

    public InstantiationStrategy getInstantiationStrategy(){
        return instantiationStrategy;
    }




}