package com.ccqstark.stugrademanage.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUser extends User {
    private String vercode;
    private String username;
    private String password;
    private String email;
    private int role;
}
