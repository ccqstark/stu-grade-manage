package com.ccqstark.stugrademanage.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
public class User implements UserDetails {
    private int user_id;
    private String username;
    private String password;
    private int role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role1="1";
        String role2="0";
        GrantedAuthority authority1=new SimpleGrantedAuthority(role1);
        GrantedAuthority authority2=new SimpleGrantedAuthority(role2);
        List<GrantedAuthority> list=new ArrayList<>();
        list.add(authority1);
        list.add(authority2);
        return list;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
