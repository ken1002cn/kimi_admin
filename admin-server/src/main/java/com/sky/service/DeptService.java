package com.sky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.entity.SysDept;
import com.sky.result.PageResult;
import com.sky.vo.BasePageReqVo;

public interface DeptService extends IService<SysDept> {
    String deleteDept(Long id);

    PageResult listDept(BasePageReqVo pageReqVo);
}
