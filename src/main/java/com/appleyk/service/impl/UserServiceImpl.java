package com.appleyk.service.impl;

import com.appleyk.common.FilterHelper;
import com.appleyk.entity.UserEntity;
import com.appleyk.exception.BaseException;
import com.appleyk.filter.UserFilter;
import com.appleyk.mapper.UserMapper;
import com.appleyk.paging.PageInfo;
import com.appleyk.pojo.User;
import com.appleyk.result.ResponseMessage;
import com.appleyk.result.ResultData;
import com.appleyk.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description 用户业务接口实现类  --> 加@Service注解进行服务注入
 * @Author Appleyk
 * @Blob https://blog.csdn.net/appleyk
 * @Date Created on 上午 11:17 2018-8-31
 */
@Service
public class UserServiceImpl implements UserService {


    // 注入mapper
    @Autowired
    private UserMapper userMapper;

    // 捕获异常，事务回滚
    @Override
    @Transactional(rollbackFor = {Exception.class,SQLException.class,BaseException.class})
    public boolean save(List<User> users) throws Exception {

        // 批量存储用户信息
        return  userMapper.insertBatch(users)>0;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,SQLException.class,BaseException.class})
    public boolean modify(List<User> users) throws Exception {

        // 批量更新用户信息
        return userMapper.updateBatch(users)>0;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,SQLException.class,BaseException.class})
    public boolean remove(Integer uID) throws Exception {

        return userMapper.deleteByPrimaryKey(uID) > 0;
    }

    @Override
    public ResultData<?> query(UserFilter filter) throws Exception {

        // 页码
        Integer pageNum   = filter.getPageNum();

        // 每页显示记录数
        Integer pageSize  = filter.getPageSize();

        // 设置分页拦截器
        PageHelper.startPage(pageNum,pageSize);
       

        FilterHelper<UserFilter> filterHelper = new FilterHelper<>(filter);
        String whereSql = filterHelper.buildWhereSql();
        String orderSql = filterHelper.getOrderBySql();

        List<UserEntity> userEntities = userMapper.getUsers(filter, whereSql,orderSql );
     
        /**
         * Page包装一下查询到的资源信息实体集合（List） 设置分页【起始页码，每页的记录数】
         */
        Page<UserEntity> page = ( Page<UserEntity>)userEntities;

        /**
         * PageInfo包装一下UserEntity【List】 ,并且根据Page对象
         * resourceInfoEntities设置total、pages、pageNum
         */
        PageInfo<UserEntity> pageinfo = new PageInfo<UserEntity>(page, userEntities);

        return new ResultData<>(ResponseMessage.OK,pageinfo);
    }
}
