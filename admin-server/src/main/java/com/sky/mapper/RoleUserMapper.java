package com.sky.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sky.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleUserMapper extends BaseMapper<UserRole> {
}
