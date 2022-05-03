package com.kwg.springframework.test;/**
 * @Auther: kwg2001
 * @Date: 2022/5/2 23:20
 * @Description:
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @program: my-spring
 *
 * @description:
 *
 * @author: Kwg
 *
 * @create: 2022-05-02 23:20
 **/
public class Test_java {
    private static final List<Integer> list=new ArrayList<>();

    public static void main(String[] args) {

        for(int i=0;i<10;i++){
            list.add(i);
        }
    }
}
