package com.github.zzay.mapper;

import com.github.zzay.entity.Menu;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zzay
 * @interfaceName MenuMapper
 * @description 菜单Mapper
 * @create 2022/04/19 22:11
 */
@Repository
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户ID查询该用户拥有的权限列表
     *
     * @param userId 指定用户ID
     * @return 指定用户拥有的权限列表
     */
    List<String> selectPermsByUserId(Long userId);

}
