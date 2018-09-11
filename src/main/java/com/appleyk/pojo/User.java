package com.appleyk.pojo;

import com.appleyk.common.JsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @Description 用户业务模型
 * @Author Appleyk
 * @Blob https://blog.csdn.net/appleyk
 * @Date Created on 上午 10:54 2018-8-31
 */
public class User {


    private Integer id;
    private String name;
    private Integer sex;

    public User() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public  static  void main(String[] args){

        List<User> users = new ArrayList<>();
        User user = new User();
        user.setName("appleyk");
        user.setSex(0);
        users.add(user);
        System.out.println(JsonUtils.objectToJson(users));
    }
}
