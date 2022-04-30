package com.github.zzay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.zzay.entity.Menu;
import com.github.zzay.entity.Role;
import com.github.zzay.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zzay
 * @interfaceName UserInfoMapper
 * @description User Information Mapper类
 * @create 2022/04/30 16:23
 */
@Repository
@Mapper
public interface UserInfoMapper extends BaseMapper<User> {

    /**
     * Select the user with the given username
     *
     * @param username Username
     * @return User with the given username
     */
    User selectByUsername(String username);

    /**
     * Select the role list of the user with the given ID
     *
     * @param userId User ID
     * @return The user's role list
     */
    List<Role> selectRoleByUserId(Long userId);

    /**
     * Select the menu list of the user with the given ID
     *
     * @param userId User ID
     * @return The user's menu list
     */
    List<Menu> selectMenuByUserId(Long userId);

}
