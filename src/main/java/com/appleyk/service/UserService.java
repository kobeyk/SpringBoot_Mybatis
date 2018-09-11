package com.appleyk.service;

import com.appleyk.filter.UserFilter;
import com.appleyk.pojo.User;
import com.appleyk.result.ResultData;

import java.util.List;

/**
 * @Description 用户业务接口
 * @Author Appleyk
 * @Blob https://blog.csdn.net/appleyk
 * @Date Created on 上午 11:03 2018-8-31
 */
public interface UserService {

    /**
     * 存储
     * @param users
     * @return
     * @throws Exception
     */
    boolean save(List<User> users) throws  Exception;

    /**
     * 更新
     * @param users
     * @return
     * @throws Exception
     */
    boolean modify(List<User> users) throws  Exception;

    /**
     * 删除
     * @param uID
     * @return
     * @throws Exception
     */
    boolean remove(Integer uID) throws  Exception;


    /**
     * 查询
     * @param filter
     * @return
     * @throws Exception
     */
    ResultData<?> query(UserFilter filter) throws  Exception;

}
