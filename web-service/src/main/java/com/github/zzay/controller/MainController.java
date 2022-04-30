package com.github.zzay.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zzay
 * @className MainController
 * @description Main Controller
 * @create 2022/04/30 22:53
 */
@Controller
@Tag(name = "MainController", description = "主要页面跳转相关接口")
public class MainController {

    /**
     * Dashboard page
     *
     * @return Dashboard page
     */
    @GetMapping({"/", "/index"})
    @Operation(summary = "主页面跳转", description = "主页面跳转", security = @SecurityRequirement(name = HttpHeaders.AUTHORIZATION))
    public String dashboardPage() {
        return "index";
    }

    /**
     * Log-in page
     *
     * @return Log-in page
     */
    @GetMapping("/login")
    @Operation(summary = "登录页面跳转", description = "登录页面跳转", security = @SecurityRequirement(name = HttpHeaders.AUTHORIZATION))
    public String loginPage() {
        return "login";
    }

    /**
     * Register page
     *
     * @return Register page
     */
    @GetMapping("/register")
    @Operation(summary = "注册页面跳转", description = "注册页面跳转", security = @SecurityRequirement(name = HttpHeaders.AUTHORIZATION))
    public String registerPage() {
        return "register";
    }

    /**
     * Customized "unauthorized" error page
     *
     * @return Customized "unauthorized" error page
     */
    @GetMapping("/unauthorized")
    @Operation(summary = "未认证错误页面跳转", description = "未认证错误页面跳转", security = @SecurityRequirement(name = HttpHeaders.AUTHORIZATION))
    public String accessDeniedPage() {
        return "unauthorized";
    }

}
