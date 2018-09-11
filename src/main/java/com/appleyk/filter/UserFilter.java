package com.appleyk.filter;

import java.util.HashSet;

/**
 * @Description 用户查询过滤器 --> 继承基础过滤器【实现分页和排序
 * @Author Appleyk
 * @Blob https://blog.csdn.net/appleyk
 * @Date Created on 上午 11:13 2018-8-31
 */
public class UserFilter  extends  AFilter{

   protected HashSet<Integer> uids  ;
   protected HashSet<String>  names ;

    public UserFilter() {
        uids  = new HashSet<>();
        names = new HashSet<>();
    }

    public HashSet<Integer> getUids() {
        return uids;
    }

    public void setUids(HashSet<Integer> uids) {
        this.uids = uids;
    }

    public HashSet<String> getNames() {
        return names;
    }

    public void setNames(HashSet<String> names) {
        this.names = names;
    }
}
