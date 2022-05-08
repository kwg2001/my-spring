package com.kwg.springframework.Context.support;/**
 * @Auther: kwg2001
 * @Date: 2022/5/7 14:15
 * @Description:
 */

import com.kwg.springframework.beans.BeansException;

import java.util.Map;

/**
 * @program: my-spring
 *
 * @description: 应用上下文的实现类
 *
 * @author: Kwg
 *
 * @create: 2022-05-07 14:15
 **/
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private String[] configLocations;

    public ClassPathXmlApplicationContext(){}

    /**
     * String 类型的构造方法其实是调用了 String[] 类型的构造方法
     * @param configLocations
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String configLocations)throws BeansException{
        this(new String[]{configLocations});
    }

    /**
     * 从xml 中加载beanDefinition 并刷新上下文
     * @param configLocations
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException{
        this.configLocations=configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}
