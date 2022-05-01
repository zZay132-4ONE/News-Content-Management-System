package com.github.zzay.controller;

import com.github.zzay.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzay
 * @className TestController
 * @description Test Controller
 * @create 2022/04/30 00:03
 */
@RestController
@RequestMapping("/test")
@Tag(name = "TestController", description = "测试操作相关接口")
public class TestController {

    /**
     * Test for basic Spring Security
     *
     * @return Response message
     */
    @GetMapping("/hello")
    @Operation(summary = "测试Spring Security", description = "测试Spring Security：从数据库读取对应用户密码并校验", security = @SecurityRequirement(name = HttpHeaders.AUTHORIZATION))
    public String hello() {
        return "hello spring security !";
    }

    /**
     * Test for {@link Secured}
     *
     * @return Response message
     */
    @GetMapping("/secured")
    @Secured({"ROLE_admin"})
    @Operation(summary = "测试@Secured注解功能", description = "测试@Secured注解功能：说明当前URL地址所需要的角色", security = @SecurityRequirement(name = HttpHeaders.AUTHORIZATION))
    public String testSecured() {
        return "test Secured";
    }

    /**
     * Test for {@link PreAuthorize}
     *
     * @return Response message
     */
    @GetMapping("/preAuth")
    @PreAuthorize("hasAnyAuthority('system:admin')")
    @Operation(summary = "测试@PreAuthorize注解功能", description = "测试@PreAuthorize注解功能：说明访问当前URL地址所需要的权限", security = @SecurityRequirement(name = HttpHeaders.AUTHORIZATION))
    public String testPreAuth() {
        return "test preAuth";
    }

    /**
     * Test for {@link PostFilter}
     *
     * @return Response message
     */
    @GetMapping("/postFilter")
    @PostFilter("filterObject.username == zzay")
    @Operation(summary = "测试@PostFilter注解功能", description = "测试@PostFilter注解功能：过滤经过校验后得到的内容", security = @SecurityRequirement(name = HttpHeaders.AUTHORIZATION))
    public List<User> testPostFilter() {
        ArrayList<User> usersArrayList = new ArrayList<>();
        usersArrayList.add(new User(1L, "zzay", "123456"));
        usersArrayList.add(new User(2L, "jay", "123456"));
        return usersArrayList;
    }

    /**
     * Test for {@link PreFilter}
     *
     * @return Response message
     */
    @GetMapping("/preFilter")
    @PreAuthorize("hasRole('ROLE_管理员')")
    @PreFilter(value = "filterObject.id % 2 == 0")
    @Operation(summary = "测试@PreFilter注解功能", description = "测试@PreFilter注解功能：过滤经过校验前传入的内容", security = @SecurityRequirement(name = HttpHeaders.AUTHORIZATION))
    public List<User> testPreFilter(List<User> list) {
        list.forEach(t -> {
            System.out.println(t.getId() + "\t" + t.getUsername());
        });
        return list;
    }

}
