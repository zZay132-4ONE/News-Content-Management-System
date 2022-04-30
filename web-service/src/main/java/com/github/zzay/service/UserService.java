package com.github.zzay.service;

import com.github.zzay.entity.dto.UserDto;

/**
 * @author zzay
 * @interfaceName UserService
 * @description User Service
 * @create 2022/04/30 23:24
 * @see com.github.zzay.service.impl.UserServiceImpl
 */
public interface UserService {

    /**
     * Register
     *
     * @param userDto User DTO
     * @return Operation result
     */
    Boolean register(UserDto userDto);

}
