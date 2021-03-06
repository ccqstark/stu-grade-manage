package com.ccqstark.stugrademanage.controller;

import com.alibaba.excel.EasyExcel;
import com.ccqstark.stugrademanage.mapper.CourseExtraMapper;
import com.ccqstark.stugrademanage.pojo.Classes;
import com.ccqstark.stugrademanage.pojo.CourseExtra;
import com.ccqstark.stugrademanage.pojo.Result;
import com.ccqstark.stugrademanage.pojo.UploadDAO;
import com.ccqstark.stugrademanage.util.UploadDataListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @Author ccqstark
 * @Description 额外课程Controller
 * @Date 2020/12/3 11:02
 **/
@Slf4j
@RestController
@RequestMapping("/grade_extra")
public class CourseExtraController {

    @Autowired
    private UploadDAO uploadDAO;

    @Autowired
    private HttpServletRequest httpServletRequest;

    private CourseExtraMapper courseExtraMapper;

    @Autowired
    public CourseExtraController(CourseExtraMapper courseExtraMapper) {
        this.courseExtraMapper = courseExtraMapper;
    }

    /**
     * @return com.ccqstark.stugrademanage.pojo.Result
     * @Author ccqstark
     * @Description 用excel导入学生成绩
     * @Date 2020/12/3 16:39
     * @Param [file]
     **/
    @PostMapping("/excel")
    public Result importGrade(MultipartFile file) throws IOException {

        if (UserController.getRoleByToken(httpServletRequest) == 0) {
            return new Result(400, "没有操作权限");
        }

        EasyExcel.read(file.getInputStream(), CourseExtra.class, new UploadDataListener(uploadDAO)).sheet().doRead();

        return new Result(200, "导入成功");
    }

    /**
     * @Author ccqstark
     * @Description 获取此额外课程的成绩列表
     * @Date  2020/12/7 2:03
     * @Param [classes]
     * @return java.util.List<com.ccqstark.stugrademanage.pojo.CourseExtra>
     **/
    @PostMapping("/one")
    public List<CourseExtra> getExtraCourseGrade(@RequestBody Classes classes) {

        String className = classes.getClass_name();
        String courseName = classes.getCourse_extra();
        List<CourseExtra> extraGradeList = courseExtraMapper.getCourseExtraGrade(className, courseName);
        return extraGradeList;
    }

}
