package com.github.zzay.service;

import com.github.zzay.entity.User;
import com.github.zzay.result.ResponseBean;

/**
 * @author zzay
 * @interfaceName LoginService
 * @description 登录Service接口
 * @create 2022/04/21 16:35
 * @see com.github.zzay.service.impl.LoginServiceImpl
 */
public interface LoginService {

    /**
     * 登录
     *
     * @param user 进行登录操作的用户
     * @return 操作响应结果
     */
    ResponseBean login(User user);

    /**
     * 登出
     *
     * @return 操作响应结果
     */
    ResponseBean logout();

}
