package com.github.zzay.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author zzay
 * @className SecurityConfigTest
 * @description SecurityConfig测试类
 * @create 2022/04/21 18:35
 */
@SpringBootTest
public class SecurityConfigTest {

    /**
     * 密码加密器
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 测试密码加密器
     */
    @Test
    public void TestBCryptPasswordEncoder() {
        String rawPwd = "123456";
        String encodedPwd = passwordEncoder.encode(rawPwd);
        boolean match = passwordEncoder.matches(rawPwd, encodedPwd);
        System.out.println("Raw password: " + rawPwd);
        System.out.println("Encoded password: " + encodedPwd);
        System.out.println("Match: " + match);
    }

}
