package com.ccqstark.stugrademanage.mapper;

import com.ccqstark.stugrademanage.pojo.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author ccqstark
 * @Description 学生mapper
 * @Date  2020/12/1 1:01
 **/
@Repository
public interface StudentMapper {

    List<Student> queryStudentListByClassID(int class_id);

    void addStudent(Student student);

    int updateGrade(Student student);

    int deleteStudent(int studentID);

}
