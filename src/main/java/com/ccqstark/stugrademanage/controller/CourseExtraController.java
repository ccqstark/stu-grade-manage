package com.ccqstark.stugrademanage.controller;

import com.alibaba.excel.EasyExcel;
import com.ccqstark.stugrademanage.pojo.CourseExtra;
import com.ccqstark.stugrademanage.pojo.Result;
import com.ccqstark.stugrademanage.pojo.UploadDAO;
import com.ccqstark.stugrademanage.util.UploadDataListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author ccqstark
 * @Description 额外课程Controller
 * @Date  2020/12/3 11:02
 **/

@RestController
@RequestMapping("/grade_extra")
public class CourseExtraController {

    private UploadDAO uploadDAO;
    @Autowired
    public CourseExtraController(UploadDAO uploadDAO){
        this.uploadDAO = uploadDAO;
    }

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * @Author ccqstark
     * @Description 用excel导入学生成绩
     * @Date  2020/12/3 16:39
     * @Param [file]
     * @return com.ccqstark.stugrademanage.pojo.Result
     **/
    @PostMapping("/excel")
    public Result importGrade(MultipartFile file)throws IOException{

        if (UserController.getRoleByToken(httpServletRequest) == 0){
            return new Result(400, "没有操作权限");
        }

        EasyExcel.read(file.getInputStream(), CourseExtra.class, new UploadDataListener(uploadDAO)).sheet().doRead();

        return new Result(200,"导入成功");
    }

}
