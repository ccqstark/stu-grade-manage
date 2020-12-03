package com.ccqstark.stugrademanage.pojo;

import com.ccqstark.stugrademanage.mapper.CourseExtraMapper;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@NoArgsConstructor
public class UploadDAO {

    private CourseExtraMapper courseExtraMapper;
    @Autowired
    public UploadDAO(CourseExtraMapper courseExtraMapper){
        this.courseExtraMapper = courseExtraMapper;
    }

    public void save(List<CourseExtra> list){
        courseExtraMapper.BatchInsertCourseExtra(list);
    }

}
