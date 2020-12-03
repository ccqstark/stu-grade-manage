package com.ccqstark.stugrademanage.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ccqstark
 * @Description 学生类，包括成绩
 * @Date  2020/12/2 21:45
 * @Param 
 * @return 
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private int studentID;
    private String number;
    private String name;
    private int math;
    private int english;
    private int java;
    private int os;
    private int sports;
    private int average;
}
