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

    private String name;
    private String company;
    private String location;
    private UserDao userDao;

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

    public String selectUser(String userId){

        return userDao.selectUserName(userId) + ",company：" + company + ",location：" + location;

    }

    @Override
    public String toString() {
        return "UserService{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
