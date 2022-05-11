package com.kwg.springframework.beans.factory;

/**
 * @Auther: kwg2001
 * @Date: 2022/5/8 16:40
 * @Description: 销毁 Bean 的接口
 */
public interface DisposableBean {

    void destory() throws Exception;
}
