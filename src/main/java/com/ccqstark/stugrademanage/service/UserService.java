package com.ccqstark.stugrademanage.service;

import com.ccqstark.stugrademanage.pojo.User;

import java.util.Map;

public interface UserService {

    public User getUserByUserName(String username);

    public Map login(String username, String password);
}
