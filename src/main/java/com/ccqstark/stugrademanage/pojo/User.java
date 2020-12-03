package com.ccqstark.stugrademanage.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ccqstark
 * @Description 用户
 * @Date  2020/12/2 23:05
 * @Param
 * @return
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String username;
    private String password;
    private int role;
}
