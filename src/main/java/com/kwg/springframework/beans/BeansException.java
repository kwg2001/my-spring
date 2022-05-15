package com.kwg.springframework.beans;/**
 * @Auther: kwg2001
 * @Date: 2022/5/2 10:26
 * @Description:
 */

/**
 * @program: my-spring
 *
 * @description: 定义异常类
 *
 * @author: Kwg
 *
 * @create: 2022-05-02 10:26
 **/
public class BeansException extends RuntimeException {

    public BeansException(String msg){
        super(msg);
    }

    public BeansException(String msg,Throwable cause){
        super(msg,cause);
    }
}
