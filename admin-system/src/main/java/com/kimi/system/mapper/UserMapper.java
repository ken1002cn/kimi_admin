package com.kimi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kimi.common.pojo.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<SysUser> {
}
