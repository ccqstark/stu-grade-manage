package com.ccqstark.stugrademanage.controller;

import com.ccqstark.stugrademanage.pojo.Result;
import com.ccqstark.stugrademanage.service.MailService;
import com.ccqstark.stugrademanage.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/email")
public class MailController {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private MailService mailService;

    @Autowired
    private RedisService redisService;

    /**
     * @Author ccqstark
     * @Description 发送验证邮件
     * @Date  2020/12/4 21:44
     * @Param []
     * @return com.ccqstark.stugrademanage.pojo.Result
     **/
    @PostMapping("/sending")
    public Result sendVerifyEmail(){

        int vercode= 1000 + (int)(Math.random() * (9999-1000));
        mailService.sendSimpleMail(
                "ccqstark@qq.com",
                "学生成绩管理系统验证码",
                "感谢注册我们的系统，您的验证码为: "+vercode+", 5分钟之内有效");

        // 获取user_id
        int user_id = UserController.getUserIdByToken(httpServletRequest);
        // 写进redis, 5分钟存活
        redisService.set("vercode"+user_id, Integer.toString(vercode), 5*60L);

        return new Result(200,"发送成功");
    }

    /**
     * @Author ccqstark
     * @Description 验证验证码
     * @Date  2020/12/4 21:45
     * @Param [vercode]
     * @return com.ccqstark.stugrademanage.pojo.Result
     **/
    @PostMapping("vercode")
    public Result verifyTheCode(String vercode){

        int user_id = UserController.getUserIdByToken(httpServletRequest);
        String vercode_cache = (String) redisService.get("vercode"+user_id);

        if (vercode.equals(vercode_cache)){
            redisService.remove("vercode"+user_id);
            return new Result(200,"验证码正确");
        }

        return new Result(400,"验证码错误");
    }


}