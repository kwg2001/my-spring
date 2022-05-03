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
    UserDao userDao;

    public UserService(){}

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserService(String name, UserDao userDao) {
        this.name = name;
        this.userDao = userDao;
    }

    public UserService(String name){
        this.name=name;
    }

    public void selectUser(String userId){
        System.out.println("select用户信息"+userDao.selectUserName(userId));
    }

    @Override
    public String toString() {
        return "UserService{" +
                "name='" + name + '\'' +
                '}';
    }
}
