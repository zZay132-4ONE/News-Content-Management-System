package com.github.zzay.mapper;

import com.github.zzay.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author zzay
 * @className UserMapperTest
 * @description UserMapper测试类
 * @create 2022/04/21 16:55
 */
@SpringBootTest
public class UserMapperTest {

    /**
     * UserMapper实例
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 测试UserMapper功能
     */
    @Test
    public void testUserMapper() {
        List<User> userList = userMapper.selectList(null);
        System.out.println(userList);
    }

}
