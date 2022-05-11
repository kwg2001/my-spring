package com.kwg.springframework.beans.factory.support;/**
 * @Auther: kwg2001
 * @Date: 2022/5/2 10:53
 * @Description:
 */

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.kwg.springframework.beans.BeansException;
import com.kwg.springframework.beans.PropertyValue;
import com.kwg.springframework.beans.PropertyValues;
import com.kwg.springframework.beans.factory.InitializingBean;
import com.kwg.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.kwg.springframework.beans.factory.config.BeanDefinition;
import com.kwg.springframework.beans.factory.config.BeanPostProcessor;
import com.kwg.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @program: my-spring
 *
 * @description: 实例化bean的类,BeanFactory的核心实现类
 *
 * @author: Kwg
 *
 * @create: 2022-05-02 10:53
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

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

            //执行bean初始化方法，和 BeanPostProcessor 的前置后置处理方法
            bean=initializaBean(beanName,bean,beanDefinition);

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

    /**
     * 初始化bean。。
     *      包括 执行 BeanPostProcessor Before 处理
     *          执行 BeanPostProcessor After 处理
     * @param beanName
     * @param bean
     * @param beanDefinition
     * @return
     */
    private Object initializaBean(String beanName,Object bean,BeanDefinition beanDefinition){

        //BeanPostProcessor Before 处理
        Object wrappedBean=applyBeanPostProcessorsBeforeInitalization(bean,beanName);

        //执行初始化方法
        try{
            invokeInitMethods(beanName,wrappedBean,beanDefinition);
        }catch (Exception e){
            throw new BeansException("Invocation of init method of bean[" + beanName + "] failed", e);
        }


        //执行 BeanPostProcessor 后置处理
        wrappedBean =applyBeanPostProcessorsAfterInitialization(wrappedBean,beanName);

        return wrappedBean;

    }

    //初始化方法
    private void invokeInitMethods(String beanName,Object bean,BeanDefinition beanDefinition) throws Exception{

        //是否实现接口InitializingBean
        if(bean instanceof InitializingBean){
            ((InitializingBean) bean).afterPropertiesSet();
        }

        //检查配置信息中的init-method属性
        String initMethodName=beanDefinition.getInitMethodName();
        if(StrUtil.isNotEmpty(initMethodName)){
            //通过反射获取这个init-method 方法
            Method initMethod =beanDefinition.getBeanClass().getMethod(initMethodName);

            //如果这个init方法为空的话 报错
            if(null==initMethod){
                throw new BeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
            }

            initMethod.invoke(bean);
        }

    }

    /**
     * 执行用户自己 扩展的 前置处理 。实例化bean的时候
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object applyBeanPostProcessorsBeforeInitalization(Object existingBean, String beanName) throws BeansException {

        Object result=existingBean;
        for(BeanPostProcessor processor : getBeanPostProcessors()){
            Object current=processor.postProcessBeforeInitialization(result,beanName);
            if(current==null) return result;
            result=current;
        }
        return result;

    }

    /**
     * 执行用户自定义的 后置处理。实例化bean的时候
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {

        Object result = existingBean;
        for(BeanPostProcessor processor :getBeanPostProcessors()){
            Object current=processor.postProcessAfterInitialization(result,beanName);
            if(current==null) return result;
            result=current;
        }
        return result;
    }



}