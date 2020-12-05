package com.ccqstark.stugrademanage.service.Impl;

import com.ccqstark.stugrademanage.mapper.UserMapper;
import com.ccqstark.stugrademanage.pojo.User;
import com.ccqstark.stugrademanage.service.UserService;
import com.ccqstark.stugrademanage.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUserDetailServiceImpl jwtUserDetailService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public User getUserByUserName(String username) {
        return userMapper.findUser(username);
    }

    @Override
    public Map login(String username, String password) {
        User userDetails = jwtUserDetailService.loadUserByUsername(username);

        if (userDetails == null) {
            Map map = new HashMap();
            map.put("code", 400);
            map.put("message", "用户不存在");
            return map;
        }

        if (!(new BCryptPasswordEncoder().matches(password, userDetails.getPassword()))) {
            Map map = new HashMap();
            map.put("code", 400);
            map.put("message", "密码错误");
            return map;
        }
        //TODO，将username和password被获得后封装到UsernamePasswordAuthenticationToken
        Authentication token = new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
        //TODO，token被传递给AuthenticationManager进行验证
        Authentication authentication = authenticationManager.authenticate(token);
        //将生成的authentication放入容器中，生成安全的上下文
        log.info("验证成功.");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //生成token
        final String realToken = jwtTokenUtil.generateToken(userDetails);

        Map map = new HashMap();
        map.put("code", 200);
        map.put("token", realToken);

        return map;
    }
}
