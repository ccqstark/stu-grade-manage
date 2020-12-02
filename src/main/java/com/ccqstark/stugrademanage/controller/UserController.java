package com.ccqstark.stugrademanage.controller;

import com.ccqstark.stugrademanage.mapper.UserMapper;
import com.ccqstark.stugrademanage.pojo.Result;
import com.ccqstark.stugrademanage.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ccqstark
 * @Description 用户Controller
 * @Date  2020/12/1 1:03
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    private UserMapper userMapper;

    @Autowired
    public UserController (UserMapper userMapper){
        this.userMapper = userMapper;
    }

    /**
     * @return com.ccqstark.stugrademanage.pojo.Result
     * @Author ccqstark
     * @Description 注册新用户  TODO: 验证，加密
     * @Date 2020/11/30 22:46
     * @Param [user]
     **/
    @PostMapping("/register")

    public Result registerUser(User user) {

        userMapper.addUser(user);

        return new Result(200, "注册成功");
    }

    /**
     * @Author ccqstark
     * @Description 用户登录  TODO: 加密，token
     * @Date  2020/11/30 23:14
     * @Param [user]
     * @return com.ccqstark.stugrademanage.pojo.Result
     **/
    @PostMapping("/login")
    public Result loginUser(User user) {

        User userFound = userMapper.findUser(user.getUsername());
        if (userFound == null) {
            return new Result(400, "此用户不存在");
        }

        if (!user.getPassword().equals(userFound.getPassword())) {
            return new Result(400, "密码错误");
        }

        return new Result(200,"登录成功");
    }

}
