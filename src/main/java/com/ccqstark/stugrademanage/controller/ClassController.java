package com.ccqstark.stugrademanage.controller;


import com.ccqstark.stugrademanage.mapper.ClassMapper;
import com.ccqstark.stugrademanage.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author ccqstark
 * @Description 班级Controller
 * @Date 2020/12/3 8:36
 **/
@RestController
@RequestMapping("/class")
public class ClassController {

    private ClassMapper classMapper;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    public ClassController(ClassMapper classMapper) {
        this.classMapper = classMapper;
    }

    /**
     * @Author ccqstark
     * @Description 新建班级
     * @Date  2020/12/3 10:55
     * @Param [className]
     * @return com.ccqstark.stugrademanage.pojo.Result
     **/
    @PostMapping("/new")
    public Result addNewClass(String className){

        if (UserController.getRoleByToken(httpServletRequest) == 0){
            return new Result(400, "没有操作权限");
        }

        classMapper.addClass(className);

        return new Result(200, "创建成功");
    }

    /**
     * @Author ccqstark
     * @Description 获取所有额外课程
     * @Date  2020/12/3 10:55
     * @Param [className]
     * @return java.lang.String[]
     **/
    @GetMapping("/course")
    public String[] getExtraCourse(String className) {

        String courseExtraStr = classMapper.queryExtraCourse(className);
        String[] courseExtraStrList = courseExtraStr.split(",");

        return courseExtraStrList;
    }


    /**
     * @Author ccqstark
     * @Description 新增额外课程
     * @Date  2020/12/3 10:55
     * @Param [className, ExtraCourse]
     * @return com.ccqstark.stugrademanage.pojo.Result
     **/
    @PostMapping("/course")
    public Result addExtraCourse(String className, String ExtraCourse) {

        if (UserController.getRoleByToken(httpServletRequest) == 0){
            return new Result(400, "没有操作权限");
        }

        String courseExtraStr = classMapper.queryExtraCourse(className);
        courseExtraStr = courseExtraStr + "," + ExtraCourse;
        classMapper.updateExtraCourse(className,courseExtraStr);

        return new Result(200,"添加成功");
    }


}
