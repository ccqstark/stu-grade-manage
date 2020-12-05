package com.ccqstark.stugrademanage.controller;

import com.alibaba.excel.EasyExcel;
import com.ccqstark.stugrademanage.mapper.StudentMapper;
import com.ccqstark.stugrademanage.pojo.Result;
import com.ccqstark.stugrademanage.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private HttpServletRequest httpServletRequest;
    
    /**
     * @Author ccqstark
     * @Description 创建新学生
     * @Date  2020/12/2 22:54
     * @Param [newStudent]
     * @return com.ccqstark.stugrademanage.pojo.Result
     **/
    @PostMapping("/new")
    public Result createStudent(Student newStudent){

        if (UserController.getRoleByToken(httpServletRequest) == 0){
            return new Result(400, "没有操作权限");
        }
        
        studentMapper.addStudent(newStudent);

        return new Result(200,"创建成功!");
    }

    /**
     * @Author ccqstark
     * @Description 获取班级学生列表 TODO: 排名算法
     * @Date  2020/12/5 21:21
     * @Param [class_id]
     * @return java.util.List<com.ccqstark.stugrademanage.pojo.Student>
     **/
    @GetMapping("/class/{class_id}")
    public List<Student> getStudentList(@PathVariable int class_id){
        List<Student> list = studentMapper.queryStudentListByClassID(class_id);
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

        if (UserController.getRoleByToken(httpServletRequest) == 0){
            return new Result(400, "没有操作权限");
        }
        
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

        if (UserController.getRoleByToken(httpServletRequest) == 0){
            return new Result(400, "没有操作权限");
        }
        
        studentMapper.deleteStudent(studentID);
        return new Result(200,"删除成功");
    }

    
    /**
     * @Author ccqstark
     * @Description 导出excel
     * @Date  2020/12/4 22:46
     * @Param [response]
     * @return void
     **/
    @GetMapping("/excel/{class_id}")
    public void exportExcel(HttpServletResponse response, @PathVariable int class_id) throws IOException {

        List<Student> list = studentMapper.queryStudentListByClassID(class_id);

        // URLEncoder.encode防止中文乱码
        String fileName = URLEncoder.encode("导出", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), Student.class).sheet("模板").doWrite(list);

    }

}
