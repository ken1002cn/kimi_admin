package com.kimi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.kimi.common.pojo.entity.SysRole;
import com.kimi.common.pojo.entity.SysUser;
import com.kimi.common.pojo.vo.BasePageReqVo;
import com.kimi.common.result.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService extends IService<SysRole> {
    String getRoleString(SysUser user);

    PageResult getPage(BasePageReqVo basePageReqVo);

    void deleteRole(Long id);

    List<SysRole> getRoleByDeptId(Long deptId);
}
