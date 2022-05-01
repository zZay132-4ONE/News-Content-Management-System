package com.github.zzay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.zzay.entity.News;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author zzay
 * @interfaceName NewsMapper
 * @description News Mapper
 * @create 2022/05/01 11:51
 */
@Repository
@Mapper
public interface NewsMapper extends BaseMapper<News> {

}
