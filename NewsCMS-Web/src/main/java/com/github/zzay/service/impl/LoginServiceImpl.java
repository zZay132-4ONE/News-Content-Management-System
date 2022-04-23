package com.github.zzay.service.impl;

import com.github.zzay.entity.LoginUser;
import com.github.zzay.entity.User;
import com.github.zzay.result.ResponseBean;
import com.github.zzay.service.LoginService;
import com.github.zzay.utils.JwtUtils;
import com.github.zzay.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author zzay
 * @className LoginServiceImpl
 * @description 登录Service实现类
 * @create 2022/04/21 16:40
 */
@Service
public class LoginServiceImpl implements LoginService {

    /**
     * AuthenticationManager实例，用于校验
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Redis缓存工具类实例，用于将用户信息存入redis
     */
    @Autowired
    private RedisCache redisCache;

    /**
     * 用户登录
     * <p>
     * （1）根据用户传入的用户信息，提取用户名和密码
     * （2）利用AuthenticationManager对用户名及密码进行校验
     * （3）判断校验结果是否为空，若非空则提取用户信息，并获得用户的id，根据id生成JWT
     * （4）将用户信息存入到redis中，以便之后校验
     * （5）将生成的JWT响应给前端
     *
     * @param user 进行登录操作的用户
     * @return 响应结果，包括根据用户唯一ID生成的JWT
     */
    @Override
    public ResponseBean login(User user) {
        // 获取传入信息，提取用于校验的信息
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        // 校验信息，得到校验结果
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authentication)) {
            throw new RuntimeException("用户名或密码错误");
        }
        // 根据校验结果获取主体（LoginUser实例），根据用户唯一ID，生成对应的JWT
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtils.createJWT(userId);
        // 将当前用户的相关校验信息存入redis，以便之后校验
        redisCache.setCacheObject("login:" + userId, loginUser);
        // 将JWT响应给前端
        HashMap<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return new ResponseBean(HttpStatus.OK.value(), "登录成功", map);
    }

    /**
     * 用户登出，并从redis中删除该用户记录
     *
     * @return 响应结果
     */
    @Override
    public ResponseBean logout() {
        // 从SecurityContext中获取Authentication（JWT认证时已存入）
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        // 在redis中删除用户记录
        redisCache.deleteObject("login:" + userId);
        return new ResponseBean(HttpStatus.OK.value(), "登出成功");
    }

}
