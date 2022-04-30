package com.github.zzay.mapper;

import com.github.zzay.entity.Menu;
import com.github.zzay.entity.Role;
import com.github.zzay.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author zzay
 * @className UserInfoMapperTest
 * @description Test Class for {@link UserMapper}
 * @create 2022/04/30 19:24
 */
@SpringBootTest
public class UserMapperTest {

    /**
     * User Information Mapper
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * Test for {@link UserMapper#selectByUsername(String)}
     */
    @Test
    public void testSelectByUsername() {
        String username = "zzay";
        User user = userMapper.selectByUsername(username);
        System.out.println(user.toString());
    }

    /**
     * Test for {@link UserMapper#selectRoleByUserId(Long)}
     */
    @Test
    public void testSelectRoleByUserId() {
        Long userId = 1L;
        List<Role> roleList = userMapper.selectRoleByUserId(userId);
        System.out.println(roleList);
    }

    /**
     * Test for {@link UserMapper#selectMenuByUserId(Long)}
     */
    @Test
    public void testSelectMenuByUserId() {
        Long userId = 1L;
        List<Menu> menuList = userMapper.selectMenuByUserId(userId);
        System.out.println(menuList);
    }

}
