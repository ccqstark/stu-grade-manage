package com.ccqstark.stugrademanage.controller;

import com.alibaba.excel.EasyExcel;
import com.ccqstark.stugrademanage.mapper.ClassMapper;
import com.ccqstark.stugrademanage.mapper.StudentMapper;
import com.ccqstark.stugrademanage.pojo.Result;
import com.ccqstark.stugrademanage.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author ccqstark
 * @Description 学生Controller
 * @Date  2020/12/1 1:02
 **/
@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentMapper studentMapper;
    private ClassMapper classMapper;
    @Autowired
    public StudentController(StudentMapper studentMapper,ClassMapper classMapper){
        this.studentMapper = studentMapper;
        this.classMapper = classMapper;
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
    public Result createStudent(@RequestBody Student newStudent){

        if (UserController.getRoleByToken(httpServletRequest) == 0){
            return new Result(400, "没有操作权限");
        }
        
        studentMapper.addStudent(newStudent);

        return new Result(200,"创建成功!");
    }

    /**
     * @Author ccqstark
     * @Description 获取班级学生列表 TODO: 排名算法
     * @Date  2020/12/6 0:10
     * @Param [class_id]
     * @return java.util.Map
     **/
    @GetMapping("/class/{class_id}")
    public Map getStudentList(@PathVariable int class_id){
        String courseExtraStr = classMapper.getExtraCourse(class_id);
        List<Student> studentList = studentMapper.queryStudentListByClassID(class_id);

        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("course_extra",courseExtraStr);
        resultMap.put("student_list",studentList);
        return resultMap;
    }

    /**
     * @Author ccqstark
     * @Description 修改一个学生的所有信息
     * @Date  2020/12/2 22:59
     * @Param [student]
     * @return com.ccqstark.stugrademanage.pojo.Result
     **/
    @PutMapping("/one")
    public Result modifyStudent(@RequestBody Student student){

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
    public Result deleteOneStudent(@RequestParam(name = "number") String number){

        if (UserController.getRoleByToken(httpServletRequest) == 0){
            return new Result(400, "没有操作权限");
        }

        System.out.println(number);

        if(studentMapper.findStudentByNumber(number)==null){
            return new Result(400,"此学生不存在");
        }
        
        studentMapper.deleteStudent(number);
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
