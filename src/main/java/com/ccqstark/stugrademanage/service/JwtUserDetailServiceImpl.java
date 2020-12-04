package com.ccqstark.stugrademanage.service;

import com.ccqstark.stugrademanage.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUserName(username);
        if(user == null) {
            log.info("用户不存在");
            throw new UsernameNotFoundException(String.format("用户名为 %s 的用户不存在！", username));
        } else {
            return  new User(user.getUsername(), user.getPassword(),user.getRole());
        }
    }
}
