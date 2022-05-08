package com.kwg.springframework.Context.support;

import com.kwg.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.kwg.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @program: my-spring
 *
 * @description: 可以加在配置文件 的 上下文抽象类
 *
 * @author: Kwg
 *
 * @create: 2022-05-07 13:45
 **/
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{

    /**
     * 加载beanDefinition 配置文件。对xml文件进行那个操作
     * @param beanFactory
     */
    @Override
    public void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        /**
         * 使用带类加载器参数的 构造器创建实例
         */
        XmlBeanDefinitionReader beanDefinitionReader=new XmlBeanDefinitionReader(beanFactory,this);
        //获取资源的地址
        String[] configLocations =getConfigLocations();

        if(configLocations!=null){
            beanDefinitionReader.loaderDefinitions(configLocations);
        }

    }

    /**
     * 抽象出一个方法,
     *  也作为上下文的入口
     */
    protected abstract String[] getConfigLocations();
}
