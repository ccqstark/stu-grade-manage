package com.ccqstark.stugrademanage.controller;


import com.ccqstark.stugrademanage.mapper.ClassMapper;
import com.ccqstark.stugrademanage.mapper.StudentMapper;
import com.ccqstark.stugrademanage.pojo.Classes;
import com.ccqstark.stugrademanage.pojo.Result;
import com.ccqstark.stugrademanage.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author ccqstark
 * @Description 班级Controller
 * @Date 2020/12/3 8:36
 **/
@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private HttpServletRequest httpServletRequest;

    private ClassMapper classMapper;
    private StudentMapper studentMapper;

    @Autowired
    public ClassController(ClassMapper classMapper, StudentMapper studentMapper) {
        this.classMapper = classMapper;
        this.studentMapper = studentMapper;
    }

    /**
     * @return java.util.Map
     * @Author ccqstark
     * @Description 登录首页获取基础信息
     * @Date 2020/12/5 21:45
     * @Param []
     **/
    @GetMapping("/index")
    public Map getIndex() {

        List<Classes> classList = classMapper.getAllClass();

        List<Student> studentsList = studentMapper.queryStudentListByClassID(classList.get(0).getClass_id());

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("class_list", classList);
        resultMap.put("student_list", studentsList);

        return resultMap;
    }

    /**
     * @return com.ccqstark.stugrademanage.pojo.Result
     * @Author ccqstark
     * @Description 新建班级
     * @Date 2020/12/3 10:55
     * @Param [className]
     **/
    @PostMapping("/new")
    public Result addNewClass(String className) {

        if (UserController.getRoleByToken(httpServletRequest) == 0) {
            return new Result(400, "没有操作权限");
        }

        if (classMapper.getClassByName(className)!=null){
            return new Result(400, "此班级已存在");
        }

        classMapper.addClass(className);

        return new Result(200, "创建成功");
    }

    /**
     * @return java.lang.String[]
     * @Author ccqstark
     * @Description 获取所有额外课程
     * @Date 2020/12/3 10:55
     * @Param [className]
     **/
    @GetMapping("/course")
    public String[] getExtraCourse(String className) {

        String courseExtraStr = classMapper.queryExtraCourse(className);
        String[] courseExtraStrList = courseExtraStr.split(",");

        return courseExtraStrList;
    }


    /**
     * @return com.ccqstark.stugrademanage.pojo.Result
     * @Author ccqstark
     * @Description 新增额外课程
     * @Date 2020/12/3 10:55
     * @Param [className, ExtraCourse]
     **/
    @PostMapping("/course")
    public Result addExtraCourse(String className, String ExtraCourse) {

        if (UserController.getRoleByToken(httpServletRequest) == 0) {
            return new Result(400, "没有操作权限");
        }

        String courseExtraStr = classMapper.queryExtraCourse(className);
        if (!courseExtraStr.equals("")){
            courseExtraStr = courseExtraStr + "," + ExtraCourse;
        } else{
            courseExtraStr = ExtraCourse;
        }

        classMapper.updateExtraCourse(className, courseExtraStr);

        return new Result(200, "添加成功");
    }


}
