package com.kwg.springframework.test;/**
 * @Auther: kwg2001
 * @Date: 2022/5/1 23:27
 * @Description:
 */

import com.kwg.springframework.beans.PropertyValue;
import com.kwg.springframework.beans.PropertyValues;
import com.kwg.springframework.beans.factory.config.BeanDefinition;
import com.kwg.springframework.beans.factory.BeanFactory;
import com.kwg.springframework.beans.factory.config.BeanReference;
import com.kwg.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.kwg.springframework.test.bean.LoginService;
import com.kwg.springframework.test.bean.UserDao;
import com.kwg.springframework.test.bean.UserService;

/**
 * @program: my-spring
 *
 * @description:
 *
 * @author: Kwg
 *
 * @create: 2022-05-01 23:27
 **/
public class Test {

    @org.junit.Test
    public void test_BeanFactory(){

         // 初始化beanfactory

        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();

         //注册bean UserDao
        BeanDefinition beanDefinition=new BeanDefinition(UserDao.class);
        beanFactory.registBeanDefinition("userDao",beanDefinition);

        //userService 属性
        PropertyValues propertyValues=new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name","hahaha"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

        //userService 注入
        BeanDefinition beanDefinition1=new BeanDefinition(UserService.class,propertyValues);
        beanFactory.registBeanDefinition("userService",beanDefinition1);


        //第一次获取使用
        UserService userService1=(UserService) beanFactory.getBean("userService","kwg");
        userService1.selectUser("1001");


        //第二次获取使用
        UserService userService2=(UserService) beanFactory.getBean("userService","kwg2");
        userService2.selectUser("1002");


        boolean flag=userService1==userService2;

        System.out.println("两次获取的bean是否是同一个单例："+flag);

    }

    @org.junit.Test
    public void test_BeanFactory2(){

        // 初始化beanfactory

        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();



        //loginService 注入
        BeanDefinition beanDefinition1=new BeanDefinition(LoginService.class);
        beanFactory.registBeanDefinition("loginService",beanDefinition1);


        //第一次获取使用
        LoginService loginService=(LoginService) beanFactory.getBean("loginService");
        loginService.login();



        System.out.println("两次获取的bean是否是同一个单例：");

    }





}
