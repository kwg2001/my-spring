package com.kwg.springframework.test;/**
 * @Auther: kwg2001
 * @Date: 2022/5/1 23:27
 * @Description:
 */

import cn.hutool.core.io.IoUtil;
import com.kwg.springframework.beans.PropertyValue;
import com.kwg.springframework.beans.PropertyValues;
import com.kwg.springframework.beans.factory.config.BeanDefinition;
import com.kwg.springframework.beans.factory.BeanFactory;
import com.kwg.springframework.beans.factory.config.BeanReference;
import com.kwg.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.kwg.springframework.beans.factory.xml.XmlBeanDefinitionReadere;
import com.kwg.springframework.core.io.DefaultResourceLoader;
import com.kwg.springframework.core.io.Resource;
import com.kwg.springframework.test.bean.LoginService;
import com.kwg.springframework.test.bean.UserDao;
import com.kwg.springframework.test.bean.UserService;
import jdk.internal.util.xml.impl.Input;
import org.junit.Before;


import java.io.IOException;
import java.io.InputStream;

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

    /**
     * 通过加载资源注入bean
     */

    private DefaultResourceLoader resourceLoader;

    //初始化
    @Before
    public void init(){
        resourceLoader=new DefaultResourceLoader();
    }

    @org.junit.Test
    public void test_classpath() throws IOException{
        Resource resource=resourceLoader.getResource("classpath:spring.xml");
       // Resource resource=resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream=resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @org.junit.Test
    public void test_file() throws IOException{
        Resource resource=resourceLoader.getResource("src/test/resources/improtant.properties");
        InputStream inputStream=resource.getInputStream();
        String content=IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    /**
     * 测试xml加载注册bean
     */
    @org.junit.Test
    public void test_xml(){
        //1.初始化
        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();

        //du文件
        XmlBeanDefinitionReadere readere=new XmlBeanDefinitionReadere(beanFactory);
        readere.loaderDefinitions("classpath:spring.xml");

        //huiqu bean
        UserService userService= (UserService) beanFactory.getBean("userService");
        userService.selectUser("1001");
    }






}
