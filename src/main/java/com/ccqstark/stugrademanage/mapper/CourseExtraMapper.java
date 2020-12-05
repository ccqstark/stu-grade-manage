package com.ccqstark.stugrademanage.mapper;


import com.ccqstark.stugrademanage.pojo.CourseExtra;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author ccqstark
 * @Description 额外课程Mapper
 * @Date  2020/12/3 11:03
 **/
@Repository
public interface CourseExtraMapper {

    int BatchInsertCourseExtra(List list);

    List<CourseExtra> getCourseExtraGrade(String className, String courseName);

}
