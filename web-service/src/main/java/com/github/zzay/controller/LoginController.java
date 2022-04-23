package com.github.zzay.controller;

import com.github.zzay.entity.User;
import com.github.zzay.result.ResponseBean;
import com.github.zzay.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author zzay
 * @className LoginController
 * @description 登录controller
 * @create 2022/04/21 14:02
 */
@RestController
@Tag(name = "LoginController", description = "用户登录相关操作接口")
public class LoginController {

    /**
     * 登录Service
     */
    @Autowired
    private LoginService loginService;

    /**
     * 用户登录
     *
     * @param user 传入的用户信息
     * @return 操作响应Bean
     */
    @Operation(summary = "用户登录", description = "用户登录操作", security = @SecurityRequirement(name = HttpHeaders.AUTHORIZATION))
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "登录成功")})
    @PreAuthorize("isAnonymous()")
    @PostMapping("/user/login")
    public ResponseBean login(
            @Parameter(name = "user", description = "用户实体信息", required = true) @RequestBody User user) {
        return loginService.login(user);
    }

    /**
     * 用户登出
     *
     * @return 操作响应Bean
     */
    @Operation(summary = "用户登出", description = "用户登出操作", security = @SecurityRequirement(name = HttpHeaders.AUTHORIZATION))
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "登出成功")})
    @PostMapping("/user/logout")
    public ResponseBean logout() {
        return loginService.logout();
    }

}
