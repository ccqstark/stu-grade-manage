package com.ccqstark.stugrademanage.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
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
    @ExcelIgnore
    private int student_id;
    @ExcelProperty("学号")
    private String number;
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("数学")
    private int math;
    @ExcelProperty("英语")
    private int english;
    @ExcelProperty("Java")
    private int java;
    @ExcelProperty("操作系统")
    private int os;
    @ExcelProperty("体育")
    private int sports;
    @ExcelIgnore
    private int average;
}
