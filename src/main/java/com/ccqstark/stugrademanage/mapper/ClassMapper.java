package com.ccqstark.stugrademanage.mapper;


import com.ccqstark.stugrademanage.pojo.Classes;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author ccqstark
 * @Description 班级mapper
 * @Date 2020/12/3 8:49
 * @Param
 * @return
 **/
@Repository
public interface ClassMapper {

    List<Classes> getAllClass();

    String getExtraCourse(int class_id);

    Classes getClassByName(String class_name);

    int addClass(String ClassName);

    String queryExtraCourse(String className);

    int updateExtraCourse(String className, String extraCourseStr);

}
