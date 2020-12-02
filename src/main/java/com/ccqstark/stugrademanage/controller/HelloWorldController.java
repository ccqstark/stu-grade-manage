package com.ccqstark.stugrademanage.controller;

import com.ccqstark.stugrademanage.pojo.Demo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ccqstark
 * @Description 测试类
 * @Date  2020/12/1 1:02
 **/
@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    @PostMapping("/ccq")
    public Demo helloWorldDemo(){
        Demo demo = new Demo();
        demo.setCode(200);
        demo.setMsg("Hello ccq");
        return demo;
    }
}
