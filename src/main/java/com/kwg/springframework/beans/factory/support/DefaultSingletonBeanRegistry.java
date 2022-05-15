package com.kwg.springframework.beans.factory.support;/**
 * @Auther: kwg2001
 * @Date: 2022/5/2 10:39
 * @Description:
 */

import com.kwg.springframework.beans.BeansException;
import com.kwg.springframework.beans.factory.DisposableBean;
import com.kwg.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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

    /**
     * 注册bean 的 销毁方法 的具体信息，
     * 最终销毁方法 可以被AbstractApplicationContext的close方法
     * 通过getBeanFactoey().destorySingletons()调用
     */
    private final Map<String, DisposableBean> disposableBeans=new HashMap<>();


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

    public void registerDisposableBean(String beanName,DisposableBean bean){
        disposableBeans.put(beanName,bean);
    }

    public void destroySingletons(){
        Set<String> keySet=this.disposableBeans.keySet();
        Object[] disposableBeanNames=keySet.toArray();

        for(int i=disposableBeanNames.length-1;i>=0;i--){
            Object beanName=disposableBeanNames[i];
            //将对象销毁后，对象的销毁方法已不需要了，直接移除
            DisposableBean disposableBean=disposableBeans.remove(beanName);

            try {
                disposableBean.destroy();
            }catch (Exception e){
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }

        }
    }
}
