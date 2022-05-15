package com.kwg.springframework.Context;

import com.kwg.springframework.beans.BeansException;

/**
 * @Auther: kwg2001
 * @Date: 2022/5/5 15:55
 * @Description:
 */
public interface ConfigurableApplication extends ApplicationContext{

    /**
     * 刷新容器。。spring中的核心方法
     * @throws BeansException
     */
    void refresh() throws BeansException;

    /**
     * 定义注册虚拟机钩子的方法
     */
    void registerShutdownHook();

    /**
     * 手动执行关闭的方法
     */
    void close();
}
