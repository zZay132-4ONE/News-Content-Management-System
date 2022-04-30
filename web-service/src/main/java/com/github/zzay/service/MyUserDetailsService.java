package com.github.zzay.service;

import com.github.zzay.entity.Menu;
import com.github.zzay.entity.Role;
import com.github.zzay.entity.User;
import com.github.zzay.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzay
 * @className MyUserDetailsService
 * @description MyUserDetailsService
 * @create 2022/04/30 13:30
 */
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    /**
     * User Information Mapper
     */
    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * Load {@link UserDetails} with the given username, and conduct some other operations
     * @param username Username
     * @return {@link UserDetails}
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // query for the users with the same username as the given one in database
        User users = userInfoMapper.selectByUsername(username);
        if (users == null) {
            throw new UsernameNotFoundException("用户名不存在！");
        }
        // get role list and menu list of the user
        List<Role> roleList = userInfoMapper.selectRoleByUserId(users.getId());
        List<Menu> menuList = userInfoMapper.selectMenuByUserId(users.getId());
        // authority list
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        // role list
        for (Role role : roleList) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + role.getName());
            grantedAuthorityList.add(simpleGrantedAuthority);
        }
        // menu list
        for (Menu menu : menuList) {
            grantedAuthorityList.add(new SimpleGrantedAuthority(menu.getPermission()));
        }
        return new org.springframework.security.core.userdetails.User(users.getUsername(), users.getPassword(), grantedAuthorityList);
    }

}
