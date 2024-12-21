package com.sky.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sky.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface RoleMapper extends BaseMapper<SysRole> {
}
