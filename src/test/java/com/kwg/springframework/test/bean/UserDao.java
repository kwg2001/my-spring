package com.kwg.springframework.test.bean;/**
 * @Auther: kwg2001
 * @Date: 2022/5/3 10:18
 * @Description:
 */

import java.util.HashMap;
import java.util.Map;

/**
 * @program: my-spring
 *
 * @description:
 *
 * @author: Kwg
 *
 * @create: 2022-05-03 10:18
 **/
public class UserDao {
    public static Map<String,String>  userMap=new HashMap<>();

    public void initDataMethod() {
        System.out.println("执行：init-method");
        userMap.put("1001","kwg001");
        userMap.put("1002","kwg002");
        userMap.put("1003","kwg003");
        userMap.put("1004","kwg004");
    }
    public void destroyDataMethod(){
        System.out.println("执行：destory-method");
        userMap.clear();
    }

    public String selectUserName(String userId) {
        return userMap.get(userId);
    }
}
