package com.kwg.springframework.beans.factory.support;/**
 * @Auther: kwg2001
 * @Date: 2022/5/2 10:39
 * @Description:
 */

import com.kwg.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: my-spring
 *
 * @description:
 *      1、注册bean的类，
 *      2、AbstractBeanFactory继承此类
 *      3、将注册bean的功能抽出来，实现一类一功能
 *      4、方便以后扩展
 *
 * @author: Kwg
 *
 * @create: 2022-05-02 10:39
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * bean单例表，
     * 线程安全
     */
    private final Map<String ,Object > singletonBeanMap=new ConcurrentHashMap<>();

    @Override
    public Object getSingleton(String name) {
        return singletonBeanMap.get(name);
    }

    /**
     * 在单例表中添加bean Object
     */
    public void addSingleton(String name,Object singletonBeanObject){
        singletonBeanMap.put(name,singletonBeanObject);
    }
}
