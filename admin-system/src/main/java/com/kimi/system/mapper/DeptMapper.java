package com.kimi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kimi.common.pojo.entity.SysDept;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptMapper extends BaseMapper<SysDept> {
}
