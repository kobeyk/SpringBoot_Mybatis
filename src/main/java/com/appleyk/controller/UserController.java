package com.appleyk.controller;

import com.appleyk.filter.UserFilter;
import com.appleyk.pojo.User;
import com.appleyk.result.ResponseMessage;
import com.appleyk.result.ResponseResult;
import com.appleyk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author Appleyk
 * @Blob https://blog.csdn.net/appleyk
 * @Date Created on 上午 11:19 2018-8-31
 */
@CrossOrigin     // 解决跨域问题
@RestController  // 相当于 @Controller + @ResponseBody
@RequestMapping("api/v1/user") // RESTful 风格的接口地址定义
public class UserController {


    //注入Service
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseResult save(@RequestBody List<User> users) throws  Exception{
        if(userService.save(users)){
            return  new ResponseResult(ResponseMessage.OK);
        }
        return new ResponseResult(ResponseMessage.FAIL);
    }

    @PostMapping("/update")
    public ResponseResult modify(@RequestBody List<User> users) throws  Exception{
        if(userService.modify(users)){
            return  new ResponseResult(ResponseMessage.OK);
        }
        return new ResponseResult(ResponseMessage.FAIL);
    }

    @PostMapping("/delete")
    public ResponseResult remove(@RequestParam(name="uid",required = false,defaultValue ="0") int uid)  throws  Exception{
        if(userService.remove(uid)){
            return  new ResponseResult(ResponseMessage.OK);
        }
        return new ResponseResult(ResponseMessage.FAIL);
    }

    @GetMapping("/query")
    public  ResponseResult query(UserFilter filter) throws  Exception{

        return new ResponseResult(userService.query(filter)) ;
    }

}
