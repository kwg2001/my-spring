package com.kwg.springframework.Context.support;/**
 * @Auther: kwg2001
 * @Date: 2022/5/6 18:31
 * @Description:
 */

import com.kwg.springframework.beans.BeansException;
import com.kwg.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.kwg.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @program: my-spring
 *
 * @description:  继承自AbstractApplicationContext。。。可刷新的上下文抽象类
 *                 实现了AbstractApplicationContext定义的一些抽象方法
 *
 * @author: Kwg
 *
 * @create: 2022-05-06 18:31
 **/
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    /**
     * 获取了 DefaultListableBeanFactory
     * 的实例化以及对资源配置的加载操作
     * @throws BeansException
     */
    @Override
    public void refershBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory=creatBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory=beanFactory;

    }
    private DefaultListableBeanFactory creatBeanFactory(){
        return new DefaultListableBeanFactory();
    }

    //将加载beanDefinition抽象出一个方法,
    //将他交给 能加载配置文件的抽象类去实现
    public abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);


    @Override
    public ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
