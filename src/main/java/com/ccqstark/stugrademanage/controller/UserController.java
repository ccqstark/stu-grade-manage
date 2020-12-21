package com.ccqstark.stugrademanage.controller;

import com.ccqstark.stugrademanage.mapper.UserMapper;
import com.ccqstark.stugrademanage.pojo.NewUser;
import com.ccqstark.stugrademanage.pojo.Result;
import com.ccqstark.stugrademanage.pojo.User;
import com.ccqstark.stugrademanage.service.MailService;
import com.ccqstark.stugrademanage.service.RedisService;
import com.ccqstark.stugrademanage.service.UserService;
import com.ccqstark.stugrademanage.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author ccqstark
 * @Description 用户Controller
 * @Date 2020/12/1 1:03
 **/
@Slf4j
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

    @Autowired
    private MailService mailService;

    @Autowired
    private RedisService redisService;

    /**
     * @return com.ccqstark.stugrademanage.pojo.Result
     * @Author ccqstark
     * @Description 注册新用户
     * @Date 2020/11/30 22:46
     * @Param [user]
     **/
    @PostMapping("/email")
    public Result registerUser(@RequestParam(name = "email") String email) {

        int vercode = 1000 + (int) (Math.random() * (9999 - 1000));
        mailService.sendSimpleMail(
                email,
                "学生成绩管理系统验证码",
                "感谢注册我们的系统，您的验证码为: " + vercode + ", 5分钟之内有效");

        // 写进redis, 5分钟存活
        redisService.set("vercode" + email, Integer.toString(vercode), 5 * 60L);

        return new Result(200, "发送成功");
    }

    @PostMapping("/ver_register")
    public Result verifyTheCode(@RequestBody NewUser user) {

        String vercode = user.getVercode();
        String vercode_cache = (String) redisService.get("vercode" + user.getEmail());

        if (vercode.equals(vercode_cache)) {
            // 验证码正确
            redisService.remove("vercode" + user.getEmail());
            if (userMapper.findUser(user.getUsername()) != null) {
                return new Result(400, "该用户名已被注册");
            }
            // 密码加密
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

            userMapper.addUser(user);

            return new Result(200, "注册成功");
        }

        return new Result(400, "验证码错误");
    }

    /**
     * @return com.ccqstark.stugrademanage.pojo.Result
     * @Author ccqstark
     * @Description 用户登录
     * @Date 2020/11/30 23:14
     * @Param [user]
     **/
    @PostMapping("/login")
    public Map loginUser(@RequestBody User user) {

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
     * @return int
     * @Author ccqstark
     * @Description 从token中获取role
     * @Date 2020/12/4 22:41
     * @Param [httpServletRequest]
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
