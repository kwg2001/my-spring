package com.kwg.springframework.beans.factory.support;/**
 * @Auther: kwg2001
 * @Date: 2022/5/3 20:55
 * @Description:
 */

import com.kwg.springframework.core.io.DefaultResourceLoader;
import com.kwg.springframework.core.io.ResourceLoader;

/**
 * @program: my-spring
 *
 * @description: BeanDefinitionReader的抽象实现类
 *          实现了getRegistry() 和 getResourceLoader()
 *
 * @author: Kwg
 *
 * @create: 2022-05-03 20:55
 **/
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

    private final BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
        this.resourceLoader=new DefaultResourceLoader();
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
