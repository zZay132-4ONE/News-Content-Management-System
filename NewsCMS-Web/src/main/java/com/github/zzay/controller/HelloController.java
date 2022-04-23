package com.github.zzay.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzay
 * @className HelloController
 * @description Hello Controller
 * @create 2022/04/19 16:07
 */
@Hidden
@RestController
public class HelloController {

    @PreAuthorize("@myExpr.hasAuthority('system:test')")
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/admin/hello")
    public String admin() {
        return "admin";
    }

    @RequestMapping("/user/hello")
    public String user() {
        return "user";
    }

}
