package com.kwg.springframework.test.common;

/**
 * @Auther: kwg2001
 * @Date: 2022/5/8 14:26
 * @Description:
 */

import com.kwg.springframework.beans.factory.config.BeanPostProcessor;
import com.kwg.springframework.test.bean.UserService;

/**
 * @program: my-spring
 *
 * @description:
 *
 * @author: Kwg
 *
 * @create: 2022-05-08 14:26
 **/
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if("userService".equals(beanName)){
            UserService userService=(UserService) bean;
            userService.setLocation("改为：北京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
