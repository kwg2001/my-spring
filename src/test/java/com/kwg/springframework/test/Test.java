package com.kwg.springframework.test;/**
 * @Auther: kwg2001
 * @Date: 2022/5/1 23:27
 * @Description:
 */

import cn.hutool.core.io.IoUtil;
import com.kwg.springframework.Context.support.ClassPathXmlApplicationContext;
import com.kwg.springframework.beans.PropertyValue;
import com.kwg.springframework.beans.PropertyValues;
import com.kwg.springframework.beans.factory.config.BeanDefinition;
import com.kwg.springframework.beans.factory.config.BeanReference;
import com.kwg.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.kwg.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.kwg.springframework.core.io.DefaultResourceLoader;
import com.kwg.springframework.core.io.Resource;
import com.kwg.springframework.test.bean.LoginService;
import com.kwg.springframework.test.bean.UserDao;
import com.kwg.springframework.test.bean.UserService;
import com.kwg.springframework.test.common.MyBeanFactoryPostProcessor;
import com.kwg.springframework.test.common.MyBeanPostProcessor;
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
        XmlBeanDefinitionReader readere=new XmlBeanDefinitionReader(beanFactory);
        readere.loaderDefinitions("classpath:spring.xml");

        //huiqu bean
        UserService userService= (UserService) beanFactory.getBean("userService");
        userService.selectUser("1001");
    }


    /**
     * 测试beanFactoryPostProcessor  BeanPostProcessor
     */
    @org.junit.Test
    public void test_BeanFactoryPostProcessorAndBeanPostProcessor(){

        //chushihua BeanFactory
        DefaultListableBeanFactory beanFactory =new DefaultListableBeanFactory();

        //读取配置文件  ，注册bean
        XmlBeanDefinitionReader reader=new XmlBeanDefinitionReader(beanFactory);
        //reader.loaderDefinitions("classpath:spring.xml");
        reader.loaderDefinitions("classpath:springPostProcessor.xml");

        //beanDefinition 加载完成，bean实例化之前，修改BeanDefinition的属性值
/*        MyBeanFactoryPostProcessor beanFactoryPostProcessor=new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);*/

        //bean实例化之后，修改Bean属性
/*        MyBeanPostProcessor beanPostProcessor=new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);*/

        //获取bean对象
        UserService userService=beanFactory.getBean("userService",UserService.class);

        String result=userService.selectUser("1001");
        System.out.println("测试结果：" +result);

    }


    @org.junit.Test
    public void test_context(){
        //初始化beanFactory"
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();
        //获取bean：userService
        UserService userService=applicationContext.getBean("userService",UserService.class);

        String result=userService.selectUser("1001");
        System.out.println("测试结果：" +result);
    }





}
