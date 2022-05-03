package com.kwg.springframework.beans.factory.config;/**
 * @Auther: kwg2001
 * @Date: 2022/5/2 23:51
 * @Description:
 */

/**
 * @program: my-spring
 *
 * @description:Bean 的引用
 *
 * @author: Kwg
 *
 * @create: 2022-05-02 23:51
 **/
public class BeanReference {
    private final String BeanName;

    public String getBeanName() {
        return BeanName;
    }

    public BeanReference(String beanName) {
        BeanName = beanName;
    }
}
