package com.kwg.springframework.beans;/**
 * @Auther: kwg2001
 * @Date: 2022/5/2 23:09
 * @Description:
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @program: my-spring
 *
 * @description: 属性信息的包装集合
 *
 * @author: Kwg
 *
 * @create: 2022-05-02 23:09
 **/
public class PropertyValues {
    private final List<PropertyValue> propertyValueList=new ArrayList<>();

    public PropertyValue[] getPropertyValues(){
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }
    public void addPropertyValue(PropertyValue propertyValue){
        propertyValueList.add(propertyValue);
    }
    public PropertyValue getPropertyValue(String propertyName){
        for(PropertyValue pv:propertyValueList){
            if(propertyName==pv.getName()){
                return pv;
            }
        }
        return null;
    }
}
