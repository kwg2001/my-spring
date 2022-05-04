package com.kwg.springframework.core.io;/**
 * @Auther: kwg2001
 * @Date: 2022/5/3 20:30
 * @Description:
 */

import cn.hutool.core.lang.Assert;

import java.io.IOException;
import java.io.InputStream;

/**
 * @program: my-spring
 *
 * @description:
 *      装饰模式，
 *      对外提供获取Resource的方法
 *
 * @author: Kwg
 *
 * @create: 2022-05-03 20:30
 **/
public class DefaultResourceLoader implements ResourceLoader{

    @Override
    public Resource getResource(String location) {

        Assert.notNull(location,"Location must not be null");
        if(location.startsWith(CLASSPATH_URL_PREFIX)){
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }
        else {
            return new FileSystemResource(location);
        }

    }
}
