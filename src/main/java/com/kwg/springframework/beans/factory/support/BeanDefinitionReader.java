package com.kwg.springframework.beans.factory.support;

import com.kwg.springframework.beans.BeansException;
import com.kwg.springframework.core.io.Resource;
import com.kwg.springframework.core.io.ResourceLoader;

import java.io.IOException;

/**
 * @Auther: kwg2001
 * @Date: 2022/5/3 20:44
 * @Description: BeanDefinition的读取接口，主要就是加载BeanDefinition的方法
 */
public interface BeanDefinitionReader {

    //BeanDefinition注册
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loaderDefinitions(Resource resource) throws BeansException, IOException;
    void loaderDefinitions(Resource... resource) throws BeansException, IOException;
    void loaderDefinitions(String... locations) throws BeansException;
    void loaderDefinitions(String location) throws BeansException;



}
