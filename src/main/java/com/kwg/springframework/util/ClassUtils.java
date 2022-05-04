package com.kwg.springframework.util;/**
 * @Auther: kwg2001
 * @Date: 2022/5/3 20:02
 * @Description:
 */

/**
 * @program: my-spring
 *
 * @description:
 *
 * @author: Kwg
 *
 * @create: 2022-05-03 20:02
 **/
public class ClassUtils {

    public static ClassLoader getDetaultClassLoader(){

        ClassLoader classLoader=null;

        classLoader=Thread.currentThread().getContextClassLoader();

        if(classLoader==null){
            //没有ContextClassLoader()，就使用此类的加载器
            classLoader=ClassUtils.class.getClassLoader();
        }

        return classLoader;
    }
}
