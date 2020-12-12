package com.ccqstark.stugrademanage.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ccqstark
 * @Description 额外课程类
 * @Date 2020/12/3 10:57
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseExtra {
    private int grade_id;
    private String number;
    private String student_name;
    private String class_name;
    private String course_name;
    private int score;
}
