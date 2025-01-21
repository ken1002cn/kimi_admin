package com.kimi.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kimi.blog.pojo.domain.TComment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<TComment> {
}
