package com.kimi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kimi.common.pojo.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleUserMapper extends BaseMapper<UserRole> {
}
