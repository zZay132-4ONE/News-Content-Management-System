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
 * @description Test Class for {@link UserInfoMapper}
 * @create 2022/04/30 19:24
 */
@SpringBootTest
public class UserInfoMapperTest {

    /**
     * User Information Mapper
     */
    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * Test for {@link UserInfoMapper#selectByUsername(String)}
     */
    @Test
    public void testSelectByUsername() {
        String username = "zzay";
        User user = userInfoMapper.selectByUsername(username);
        System.out.println(user.toString());
    }

    /**
     * Test for {@link UserInfoMapper#selectRoleByUserId(Long)}
     */
    @Test
    public void testSelectRoleByUserId() {
        Long userId = 1L;
        List<Role> roleList = userInfoMapper.selectRoleByUserId(userId);
        System.out.println(roleList);
    }

    /**
     * Test for {@link UserInfoMapper#selectMenuByUserId(Long)}
     */
    @Test
    public void testSelectMenuByUserId() {
        Long userId = 1L;
        List<Menu> menuList = userInfoMapper.selectMenuByUserId(userId);
        System.out.println(menuList);
    }

}
