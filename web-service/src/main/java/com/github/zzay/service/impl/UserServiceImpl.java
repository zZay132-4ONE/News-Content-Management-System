package com.github.zzay.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zzay.entity.User;
import com.github.zzay.entity.dto.UserDto;
import com.github.zzay.mapper.UserMapper;
import com.github.zzay.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author zzay
 * @className UserServiceImpl
 * @description User Service Implementation
 * @create 2022/04/30 23:29
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * User Mapper
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * Register
     *
     * @param userDto User DTO
     * @return Operation result
     */
    @Override
    public Boolean register(UserDto userDto) {
        // get username and password
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        // check whether the username has been user or not
        User user = userMapper.selectByUsername(username);
        if (user != null) {
            return false;
        }
        // create a new user record and insert it
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(new BCryptPasswordEncoder().encode(password));
        log.info("======== Register: " + username + "-" + password + " ========");
        return this.save(newUser);
    }

}
