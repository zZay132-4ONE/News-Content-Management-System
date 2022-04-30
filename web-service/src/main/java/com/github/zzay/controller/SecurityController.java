package com.github.zzay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zzay
 * @className SecurityController
 * @description Security Controller
 * @create 2022/04/30 17:00
 */
@Controller
@RequestMapping("/security")
public class SecurityController {

    /**
     * Test for customized "unauthorized" error page
     *
     * @return Url of customized "unauthorized" error page
     */
    @GetMapping("/unauth")
    public String accessDeniedPage() {
        return "unauth";
    }

}
