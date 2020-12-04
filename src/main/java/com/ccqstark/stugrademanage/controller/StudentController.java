package com.ccqstark.stugrademanage.controller;

import com.alibaba.excel.EasyExcel;
import com.ccqstark.stugrademanage.mapper.StudentMapper;
import com.ccqstark.stugrademanage.pojo.Result;
import com.ccqstark.stugrademanage.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

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

    /**
     * @Author ccqstark
     * @Description 创建新学生
     * @Date  2020/12/2 22:54
     * @Param [newStudent]
     * @return com.ccqstark.stugrademanage.pojo.Result
     **/
    @PostMapping("/new")
    public Result createStudent(Student newStudent){
        studentMapper.addStudent(newStudent);

        return new Result(200,"创建成功!");
    }

    /**
     * @Author ccqstark
     * @Description 获取学生列表 TODO: 排名算法
     * @Date  2020/12/2 22:55
     * @Param []
     * @return java.util.List<com.ccqstark.stugrademanage.pojo.Student>
     **/
    @GetMapping("/all")
    public List<Student> getStudentList(){
        List<Student> list = studentMapper.queryStudentList();
        return list;
    }

    /**
     * @Author ccqstark
     * @Description 修改一个学生的所有信息
     * @Date  2020/12/2 22:59
     * @Param [student]
     * @return com.ccqstark.stugrademanage.pojo.Result
     **/
    @PutMapping("/one")
    public Result modifyStudent(Student student){
        studentMapper.updateGrade(student);
        return new Result(200,"修改成功");
    }

    /**
     * @Author ccqstark
     * @Description 删除一个学生
     * @Date  2020/12/2 22:59
     * @Param [studentID]
     * @return com.ccqstark.stugrademanage.pojo.Result
     **/
    @DeleteMapping("/one")
    public Result deleteOneStudent(int studentID){
        studentMapper.deleteStudent(studentID);
        return new Result(200,"删除成功");
    }


    @GetMapping("/excel")
    public void download(HttpServletResponse response) throws IOException {

        List<Student> list = studentMapper.queryStudentList();

        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), Student.class).sheet("模板").doWrite(list);

    }

}
