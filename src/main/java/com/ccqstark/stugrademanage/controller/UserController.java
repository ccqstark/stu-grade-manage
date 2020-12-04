package com.ccqstark.stugrademanage.controller;

import com.ccqstark.stugrademanage.mapper.UserMapper;
import com.ccqstark.stugrademanage.pojo.Result;
import com.ccqstark.stugrademanage.pojo.User;
import com.ccqstark.stugrademanage.service.UserService;
import com.ccqstark.stugrademanage.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author ccqstark
 * @Description 用户Controller
 * @Date 2020/12/1 1:03
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    private UserMapper userMapper;

    @Autowired
    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * @return com.ccqstark.stugrademanage.pojo.Result
     * @Author ccqstark
     * @Description 注册新用户  TODO: 验证，加密
     * @Date 2020/11/30 22:46
     * @Param [user]
     **/
    @PostMapping("/register")
    public Result registerUser(User user) {

        if (userMapper.findUser(user.getUsername()) != null) {
            return new Result(400, "该用户名已被注册");
        }
        // 密码加密
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        userMapper.addUser(user);

        return new Result(200, "注册成功");
    }

    /**
     * @return com.ccqstark.stugrademanage.pojo.Result
     * @Author ccqstark
     * @Description 用户登录  TODO: 加密，token
     * @Date 2020/11/30 23:14
     * @Param [user]
     **/
    @PostMapping("/login")
    public Map loginUser(User user) {

        return userService.login(user.getUsername(), user.getPassword());
    }

    /**
     * @return int
     * @Author ccqstark
     * @Description 从token中获取user_id
     * @Date 2020/12/4 21:48
     * @Param [httpServletRequest]
     **/
    public static int getUserIdByToken(HttpServletRequest httpServletRequest) {
        // 从token中playload获取user_id
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        String Token = httpServletRequest.getHeader("Authorization");
        Map<String, Object> claims = jwtTokenUtil.getClaimsFromToken(Token);
        int user_id = (int) claims.get("user_id");
        return user_id;
    }

    /**
     * @Author ccqstark
     * @Description 从token中获取role
     * @Date  2020/12/4 22:41
     * @Param [httpServletRequest]
     * @return int
     **/
    public static int getRoleByToken(HttpServletRequest httpServletRequest) {
        // 从token中playload获取user_id
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        String Token = httpServletRequest.getHeader("Authorization");
        Map<String, Object> claims = jwtTokenUtil.getClaimsFromToken(Token);
        int role = (int) claims.get("role");
        return role;
    }

}
