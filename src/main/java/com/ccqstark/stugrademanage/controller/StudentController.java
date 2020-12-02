package com.ccqstark.stugrademanage.controller;

import com.ccqstark.stugrademanage.mapper.StudentMapper;
import com.ccqstark.stugrademanage.pojo.Result;
import com.ccqstark.stugrademanage.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ccqstark
 * @Description 学生Controller
 * @Date  2020/12/1 1:02
 **/
@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentMapper studentMapper;
    @Autowired
    public StudentController(StudentMapper studentMapper){
        this.studentMapper = studentMapper;
    }

    @PostMapping("/add")
    public Result createStudent(Student newStudent){
        studentMapper.addStudent(newStudent);

        return new Result(200,"创建成功!");
    }

}
