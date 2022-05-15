package com.kwg.springframework.core.io;/**
 * @Auther: kwg2001
 * @Date: 2022/5/3 19:47
 * @Description:
 */

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ClassUtil;
import com.kwg.springframework.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: my-spring
 *
 * @description: 读取 ClassPath下的文件
 *
 * @author: Kwg
 *
 * @create: 2022-05-03 19:47
 **/
public class ClassPathResource implements Resource {

    private final String path;

    private ClassLoader classLoader;

    //没有classLoader也要定义一个null
    public ClassPathResource(String path){
//        this.path=path;
//        this.classLoader=null;
        this(path,(ClassLoader) null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {

        Assert.notNull(path,"Path must not be null");
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader: ClassUtils.getDetaultClassLoader());

    }

    @Override
    public InputStream getInputStream() throws IOException {
        //具体的读取过程
        InputStream is=classLoader.getResourceAsStream(path);
        if(is==null){
            throw new FileNotFoundException(
                    this.path+" cannot be opened because it dose not exist"
            );
        }
        return is;
    }
}
