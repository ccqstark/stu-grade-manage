package com.ccqstark.stugrademanage.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ccqstark
 * @Description 班级类
 * @Date  2020/12/3 8:42
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Class {
    private int classID;
    private String className;
    private String courseExtra;
}
