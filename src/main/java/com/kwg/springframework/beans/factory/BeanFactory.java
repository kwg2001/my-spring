package com.kwg.springframework.beans.factory;/**
 * @Auther: kwg2001
 * @Date: 2022/5/1 23:04
 * @Description:
 */

import com.kwg.springframework.beans.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: my-spring
 *
 * @description: bean工厂
 *
 * @author: Kwg
 *
 * @create: 2022-05-01 23:04
 **/
public interface BeanFactory {

    /**
     * 获取bean方法
     * @param name
     * @return
     */
    public Object getBean(String name);

    /**
     * 可以再实例化的时候，传入参数
     * @param name
     * @param args
     * @return
     */
    public Object getBean(String name,Object... args);


}
