package com.ccqstark.stugrademanage;

import com.ccqstark.stugrademanage.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MailTest {

    @Autowired
    private MailService mailService;

    @Test
    public void contextLoads(){
        mailService.sendSimpleMail("1367305698@qq.com","springboot发邮件", "成功了！");
    }
}
