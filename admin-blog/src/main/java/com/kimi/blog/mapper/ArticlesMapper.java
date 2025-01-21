package com.kimi.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kimi.blog.pojo.domain.TArticles;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticlesMapper extends BaseMapper<TArticles> {
}
