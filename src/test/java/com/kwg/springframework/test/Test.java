package com.kwg.springframework.test;/**
 * @Auther: kwg2001
 * @Date: 2022/5/1 23:27
 * @Description:
 */

import com.kwg.springframework.beans.factory.config.BeanDefinition;
import com.kwg.springframework.beans.factory.BeanFactory;
import com.kwg.springframework.beans.factory.support.DefaultListableBeanFactory;
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

         //注册bean
        BeanDefinition beanDefinition=new BeanDefinition(UserService.class);
        beanFactory.registBeanDefinition("userService",beanDefinition);

        //第一次获取使用
        UserService userService1=(UserService) beanFactory.getBean("userService","kwg");
        userService1.selectUser();
        System.out.println("第一次使用:");
        System.out.println(userService1);

        //第二次获取使用
        UserService userService2=(UserService) beanFactory.getBean("userService","kwg2");
        userService2.selectUser();
        System.out.println("第二次使用");
        System.out.println(userService2);

        boolean flag=userService1==userService2;

        System.out.println("两次获取的bean是否是同一个单例："+flag);

    }

    @org.junit.Test
    public void test_newInstance() throws InstantiationException, IllegalAccessException {
        UserService userService=UserService.class.newInstance();
        System.out.println(userService);
    }

    @org.junit.Test
    public void test_constructor(){

    }



}
