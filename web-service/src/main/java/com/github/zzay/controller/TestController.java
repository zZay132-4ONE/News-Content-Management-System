package com.github.zzay.controller;

import com.github.zzay.entity.User;
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
public class TestController {

    /**
     * Test for basic Spring Security
     *
     * @return Response message
     */
    @GetMapping("/hello")
    public String hello() {
        return "hello spring security !";
    }

    /**
     * Test for {@link Secured}
     *
     * @return Response message
     */
    @RequestMapping("/secure")
    @Secured({"ROLE_管理员"})
    public String testSecure() {
        return "test Secure";
    }

    /**
     * Test for {@link PreAuthorize}
     *
     * @return Response message
     */
    @RequestMapping("/preAuth")
    @PreAuthorize("hasAnyAuthority('system:admin')")
    public String testPreAuth() {
        return "test preAuth";
    }

    /**
     * Test for {@link PostFilter}
     *
     * @return Response message
     */
    @RequestMapping("/postFilter")
    @PostFilter("filterObject.username == zzay")
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
    @RequestMapping("/preFilter")
    @PreAuthorize("hasRole('ROLE_管理员')")
    @PreFilter(value = "filterObject.id % 2 == 0")
    public List<User> testPreFilter(List<User> list) {
        list.forEach(t -> {
            System.out.println(t.getId() + "\t" + t.getUsername());
        });
        return list;
    }

}
