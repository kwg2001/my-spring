package com.kwg.springframework.beans.factory;

import com.kwg.springframework.beans.BeansException;

import java.util.Map;

/**
 * @Auther: kwg2001
 * @Date: 2022/5/4 17:23
 * @Description: 对BeanFactory进行扩展
 *  扩展Bean工厂 的接口
 */
public interface ListableBeanFactory extends BeanFactory{

    /**
     * 按照类型返回 bean 实例。。
     *      去核心实现类DefaultListableBeanFactory 实现
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String ,T> getBeansOfType(Class<T> type) throws BeansException;

}
