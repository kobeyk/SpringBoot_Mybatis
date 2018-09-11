package com.appleyk.mapper;

import com.appleyk.entity.UserEntity;
import com.appleyk.filter.UserFilter;
import com.appleyk.pojo.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Description 通用Mapper 实现 用户数据实体的增删改查（Dao层）
 * @Author Appleyk
 * @Blob https://blog.csdn.net/appleyk
 * @Date Created on 上午 10:55 2018-8-31
 */
public interface UserMapper extends Mapper<UserEntity> {

    /**
     * 批量插入
     * @param users
     * @return
     */
    long insertBatch(@Param("users") List<User> users);

    /**
     * 批量更新
     * @param users
     * @return
     */
    long updateBatch(@Param("users") List<User> users);

    /**
     * 根据查询过滤器+条件语句+排序条件查询用户列表信息
     * @param filter
     * @param whereSql
     * @param orderSql
     * @return
     */
    List<UserEntity> getUsers(@Param("filter")  UserFilter filter,
                        @Param("whereSql") String whereSql,
                        @Param("orderSql") String orderSql);

}
