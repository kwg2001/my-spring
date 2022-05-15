package com.kwg.springframework.core.io;/**
 * @Auther: kwg2001
 * @Date: 2022/5/3 20:25
 * @Description:
 */

/**
 * @program: my-spring
 *
 * @description: 包装定义的 获取Resource的方式
 *
 * @author: Kwg
 *
 * @create: 2022-05-03 20:25
 **/
public interface ResourceLoader {
    //包装的前缀
    String CLASSPATH_URL_PREFIX="classpath:";

    Resource getResource(String location);
}
