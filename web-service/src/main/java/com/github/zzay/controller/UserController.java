package com.github.zzay.controller;

import com.github.zzay.entity.dto.UserDto;
import com.github.zzay.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zzay
 * @className UserController
 * @description User Controller
 * @create 2022/04/30 23:39
 */
@Controller
@RequestMapping("/user")
@Tag(name = "UserController", description = "用户信息操作相关接口")
public class UserController {

    /**
     * User Service
     */
    @Autowired
    private UserService userService;

    /**
     * Register
     *
     * @param model   Model
     * @param userDto User DTO
     * @return Result page
     */
    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    @Operation(summary = "用户注册", description = "用户注册：用户通过注册表单输入用户名和密码，执行注册操作", security = @SecurityRequirement(name = HttpHeaders.AUTHORIZATION))
    public String register(@Parameter(name = "model") Model model,
                           @Parameter(name = "userDto") UserDto userDto) {
        // check whether the username has been used or not
        if (userService.register(userDto)) {
            return "login";
        }
        // if the username has been used, ask the user to re-register
        model.addAttribute("exist", true);
        return "register";
    }

}
