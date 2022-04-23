package com.github.zzay.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author zzay
 * @className MenuMapperTest
 * @description MenuMapper测试类
 * @create 2022/04/22 15:31
 */
@SpringBootTest
public class MenuMapperTest {

    /**
     * 菜单Mapper实例
     */
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 测试#selectPermsByUserId
     */
    @Test
    public void testSelectPermsByUserId() {
        List<String> permissionsList = menuMapper.selectPermsByUserId(1L);
        System.out.println("permissions: " + permissionsList);
    }

}
