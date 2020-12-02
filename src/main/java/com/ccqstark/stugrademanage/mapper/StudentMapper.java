package com.ccqstark.stugrademanage.mapper;

import com.ccqstark.stugrademanage.pojo.Student;
import org.springframework.stereotype.Repository;

/**
 * @Author ccqstark
 * @Description 学生mapper
 * @Date  2020/12/1 1:01
 **/
@Repository
public interface StudentMapper {

//    List<Student> queryStudentList();

    int addStudent(Student student);
}
