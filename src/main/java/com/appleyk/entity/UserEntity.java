package com.appleyk.entity;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description 用户数据实体模型
 * @Author Appleyk
 * @Blob https://blog.csdn.net/appleyk
 * @Date Created on 上午 10:51 2018-8-31
 */
@Table(name="user")
public class UserEntity {

    @Id
    private Integer id;
    private String name;
    private Integer sex;



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
}



