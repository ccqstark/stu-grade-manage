package com.ccqstark.stugrademanage.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String number;
    private String name;
    private int math;
    private int english;
    private int java;
    private int os;
    private int sports;
    private int average;
}
