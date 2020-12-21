package com.ccqstark.stugrademanage.controller;

import com.alibaba.excel.EasyExcel;
import com.ccqstark.stugrademanage.mapper.ClassMapper;
import com.ccqstark.stugrademanage.mapper.StudentMapper;
import com.ccqstark.stugrademanage.pojo.Result;
import com.ccqstark.stugrademanage.pojo.Student;
import com.ccqstark.stugrademanage.util.KmpUtil;
import com.ccqstark.stugrademanage.util.MergeSortUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author ccqstark
 * @Description 学生Controller
 * @Date 2020/12/1 1:02
 **/
@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentMapper studentMapper;
    private ClassMapper classMapper;

    @Autowired
    public StudentController(StudentMapper studentMapper, ClassMapper classMapper) {
        this.studentMapper = studentMapper;
        this.classMapper = classMapper;
    }

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * @return com.ccqstark.stugrademanage.pojo.Result
     * @Author ccqstark
     * @Description 创建新学生
     * @Date 2020/12/2 22:54
     * @Param [newStudent]
     **/
    @PostMapping("/new")
    public Result createStudent(@RequestBody Student newStudent) {

        if (UserController.getRoleByToken(httpServletRequest) == 0) {
            return new Result(400, "没有操作权限");
        }

        studentMapper.addStudent(newStudent);

        return new Result(200, "创建成功!");
    }

    /**
     * @return java.util.Map
     * @Author ccqstark
     * @Description 获取班级学生列表
     * @Date 2020/12/6 0:10
     * @Param [class_id]
     **/
    @GetMapping("/class/{class_id}")
    public Map getStudentList(@PathVariable int class_id) {
        String courseExtraStr = classMapper.getExtraCourse(class_id);
        List<Student> studentList = studentMapper.queryStudentListByClassID(class_id);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("course_extra", courseExtraStr);
        resultMap.put("student_list", studentList);
        return resultMap;
    }

    /**
     * @return java.util.List<com.ccqstark.stugrademanage.pojo.Student>
     * @Author ccqstark
     * @Description 平均分+排序算法
     * @Date 2020/12/20
     * @Param [class_id]
     **/
    @GetMapping("/average/{class_id}")
    public List<Student> getAverage(@PathVariable int class_id) {
        List<Student> studentList = studentMapper.queryStudentListByClassID(class_id);

        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            // 求平均分放在math字段
            student.setMath((student.getMath() + student.getEnglish() + student.getJava() + student.getOs() + student.getSports()) / 5);
        }

        // 归并排序
        MergeSortUtil.mergeSort(studentList, 0, studentList.size() - 1);

        return studentList;
    }

    /**
     * @return java.util.List<com.ccqstark.stugrademanage.pojo.Student>
     * @Author ccqstark
     * @Description 用学号搜索学生 kmp模糊搜索
     * @Date 2020/12/21 11:52
     * @Param [number]
     **/
    @GetMapping("/search/{class_id}")
    public List<Student> searchStudentByNumber(@PathVariable int class_id, @RequestParam(name = "key_word") String subStr) {

        List<Student> studentList = studentMapper.queryStudentListByClassID(class_id);
        List<Student> searchResult = new ArrayList<Student>();
        // 迭代搜索
        for (int i = 0; i < studentList.size(); i++) {
            String mainString = studentList.get(i).getNumber();
            if (KmpUtil.kmpMatch(mainString, subStr) != -1) {
                searchResult.add(studentList.get(i));
            }
        }

        return searchResult;
    }

    /**
     * @return com.ccqstark.stugrademanage.pojo.Result
     * @Author ccqstark
     * @Description 修改一个学生的所有信息
     * @Date 2020/12/2 22:59
     * @Param [student]
     **/
    @PutMapping("/one")
    public Result modifyStudent(@RequestBody Student student) {

        if (UserController.getRoleByToken(httpServletRequest) == 0) {
            return new Result(400, "没有操作权限");
        }
        studentMapper.updateGrade(student);
        return new Result(200, "修改成功");
    }

    /**
     * @return com.ccqstark.stugrademanage.pojo.Result
     * @Author ccqstark
     * @Description 删除一个学生
     * @Date 2020/12/2 22:59
     * @Param [studentID]
     **/
    @DeleteMapping("/one")
    public Result deleteOneStudent(@RequestParam(name = "number") String number) {

        if (UserController.getRoleByToken(httpServletRequest) == 0) {
            return new Result(400, "没有操作权限");
        }

        System.out.println(number);

        if (studentMapper.findStudentByNumber(number) == null) {
            return new Result(400, "此学生不存在");
        }

        studentMapper.deleteStudent(number);
        return new Result(200, "删除成功");
    }

    /**
     * @return void
     * @Author ccqstark
     * @Description 导出excel
     * @Date 2020/12/4 22:46
     * @Param [response]
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
