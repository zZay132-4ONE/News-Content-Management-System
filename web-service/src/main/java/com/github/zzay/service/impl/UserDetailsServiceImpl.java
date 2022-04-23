package com.github.zzay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.zzay.entity.User;
import com.github.zzay.entity.LoginUser;
//import com.github.zzay.mapper.MenuMapper;
import com.github.zzay.mapper.MenuMapper;
import com.github.zzay.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 自定义UserDetailsService，用于根据传入用户名，
 * 查找到对应的用户，并将该用户的用户信息及校验信息传回给AuthenticationProvider进行校验
 *
 * @author zzay
 * @className UserDetailsServiceImpl
 * @description UserDetailsService实现类
 * @create 2022/04/21 17:15
 */
@Service("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * 用户Mapper
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 菜单Mapper
     */
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 根据给定用户名，查询对应的UserDetails并返回
     *
     * @param username 给定用户名
     * @return 给定用户名对应的UserDetails
     * @throws UsernameNotFoundException 未找到用户名异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 构造queryWrapper
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        // 查询UserDetails
        User user = userMapper.selectOne(queryWrapper);
        // 若没有查询到对应的用户，则抛出异常
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名或者密码错误");
        }
        // 根据用户ID，查询该用户拥有的权限列表
        List<String> permissionKeyList = menuMapper.selectPermsByUserId(user.getId());
        // 将查询到的数据（用户信息，权限列表）封装成UserDetails并返回
        return new LoginUser(user, permissionKeyList);
    }

}
