package com.kwg.springframework.test.bean;/**
 * @Auther: kwg2001
 * @Date: 2022/5/1 23:24
 * @Description:
 */

import javax.swing.*;

/**
 * @program: my-spring
 *
 * @description:
 *
 * @author: Kwg
 *
 * @create: 2022-05-01 23:24
 **/
public class UserService {

    String name;

    public UserService(){}

    public UserService(String name){
        this.name=name;
    }

    public void selectUser(){
        System.out.println("select用户信息");
    }

    @Override
    public String toString() {
        return "UserService{" +
                "name='" + name + '\'' +
                '}';
    }
}
