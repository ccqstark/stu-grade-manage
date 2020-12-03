package com.ccqstark.stugrademanage.mapper;


import org.springframework.stereotype.Repository;

/**
 * @Author ccqstark
 * @Description 班级mapper
 * @Date 2020/12/3 8:49
 * @Param
 * @return
 **/
@Repository
public interface ClassMapper {

    int addClass(String ClassName);

    String queryExtraCourse(String className);

    int updateExtraCourse(String className, String extraCourseStr);

}
