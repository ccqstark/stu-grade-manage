package com.ccqstark.stugrademanage.util;

import com.ccqstark.stugrademanage.pojo.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ccqstark
 * @Description 归并排序工具类
 * @Date 2020/12/20 21:05
 **/
public class MergeSortUtil {

    public static void main(String[] args) {

        Student student1 = new Student();
        Student student2 = new Student();
        Student student3 = new Student();
        Student student4 = new Student();
        Student student5 = new Student();
        Student student6 = new Student();

        student1.setMath(88);
        student2.setMath(90);
        student3.setMath(75);
        student4.setMath(98);
        student5.setMath(66);
        student6.setMath(71);

        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);
        list.add(student5);
        list.add(student6);

        for (Student student : list) {
            System.out.println(student);
        }

        mergeSort(list, 0, list.size() - 1);

        System.out.println("-------------------------");

        for (Student student : list) {
            System.out.println(student);
        }

    }

    public static void mergeSort(List<Student> arr, int l, int r) {
        if (l < r) {
            int q = (l + r) / 2;
            mergeSort(arr, l, q);
            mergeSort(arr, q + 1, r);
            merge(arr, l, q, r);
        }
    }

    public static void merge(List<Student> arr, int l, int q, int r) {

        final int n1 = q - l + 1;
        final int n2 = r - q;
        List<Student> left = new ArrayList<>();
        List<Student> right = new ArrayList<>();

        for (int i = 0; i < n1; i++) {
            left.add(arr.get(l + i));
        }
        for (int i = 0; i < n2; i++) {
            right.add(arr.get(q + 1 + i));
        }

        Student stopSign = new Student();
        stopSign.setMath(-Integer.MAX_VALUE);
        left.add(stopSign);
        right.add(stopSign);

        int i = 0, j = 0;
        for (int k = l; k <= r; k++) {
            if (left.get(i).getMath() >= right.get(j).getMath()) {
                arr.set(k, left.get(i));
                i = i + 1;
            } else {
                arr.set(k, right.get(j));
                j = j + 1;
            }
        }
    }
}


