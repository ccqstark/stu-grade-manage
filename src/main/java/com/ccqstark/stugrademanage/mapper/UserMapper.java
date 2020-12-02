package com.ccqstark.stugrademanage.mapper;

import com.ccqstark.stugrademanage.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @Author ccqstark
 * @Description 用户Mapper
 * @Date  2020/12/1 1:01
 **/
@Repository
public interface UserMapper {

    int addUser(User user);

    User findUser(String username);

}
