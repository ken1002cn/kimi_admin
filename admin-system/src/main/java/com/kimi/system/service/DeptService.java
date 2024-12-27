package com.kimi.system.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.kimi.common.pojo.entity.SysDept;
import com.kimi.common.pojo.vo.BasePageReqVo;
import com.kimi.common.result.PageResult;


public interface DeptService extends IService<SysDept> {
    void deleteDept(Long id);

    PageResult listDept(BasePageReqVo pageReqVo);
}
