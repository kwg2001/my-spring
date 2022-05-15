package com.kwg.springframework.beans.factory;

/**
 * @Auther: kwg2001
 * @Date: 2022/5/8 16:40
 * @Description: 销毁 Bean 的接口
 */
public interface DisposableBean {

    /**
     * 销毁bean的方法
     * @throws Exception
     */
    void destroy() throws Exception;
}
