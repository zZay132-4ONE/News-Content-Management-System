package com.github.zzay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.zzay.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author zzay
 * @interfaceName UserMapper
 * @description 用户Mapper接口
 * @create 2022/04/21 14:58
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
