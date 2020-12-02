package com.ccqstark.stugrademanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ccqstark.stugrademanage.mapper")
public class StuGradeManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(StuGradeManageApplication.class, args);
    }

}
