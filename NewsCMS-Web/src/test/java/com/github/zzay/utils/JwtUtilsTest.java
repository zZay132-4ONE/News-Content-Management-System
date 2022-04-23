package com.github.zzay.utils;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zzay
 * @className JwtUtilsTest
 * @description JWT工具类测试类
 * @create 2022/04/21 21:42
 */
@SpringBootTest
public class JwtUtilsTest {

    /**
     * 测试JWT工具类
     */
    @Test
    public void testJwtUtils() {
        // 创建JWT
        String jwt = JwtUtils.createJWT("123456");
        // 解析JWT
        Claims claims = JwtUtils.parseJWT(jwt);
        // 通过JWT解析结果，获取加密前的原文
        String subject = claims.getSubject();
        System.out.println(jwt);
        System.out.println(claims);
        System.out.println(subject);
    }

}
