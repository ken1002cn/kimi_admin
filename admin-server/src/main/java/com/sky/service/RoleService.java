package com.sky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.entity.SysRole;
import com.sky.entity.SysUser;
import com.sky.entity.UserRole;
import com.sky.result.PageResult;
import com.sky.vo.BasePageReqVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService extends IService<SysRole> {
    String getRoleString(SysUser user);

    PageResult getPage(BasePageReqVo basePageReqVo);

    void deleteRole(Long id);

    List<SysRole> getRoleByDeptId(Long deptId);
}
