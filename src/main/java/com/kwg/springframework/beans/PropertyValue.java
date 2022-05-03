package com.kwg.springframework.beans;/**
 * @Auther: kwg2001
 * @Date: 2022/5/2 22:59
 * @Description:
 */

/**
 * @program: my-spring
 *
 * @description: 属性依赖类，属性信息
 *
 * @author: Kwg
 *
 * @create: 2022-05-02 22:59
 **/
public class PropertyValue {
    private final  String name;
    private final  Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
