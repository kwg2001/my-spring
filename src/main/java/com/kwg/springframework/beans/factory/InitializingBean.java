package com.kwg.springframework.beans.factory;

/**
 * @Auther: kwg2001
 * @Date: 2022/5/8 16:37
 * @Description: 初始化bean 的接口
 */
public interface InitializingBean {

    /**
     * 在填充完Bean属性后 调用
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
}
