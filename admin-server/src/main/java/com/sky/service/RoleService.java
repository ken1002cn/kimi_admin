package com.sky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.entity.SysRole;
import com.sky.entity.SysUser;
import com.sky.entity.UserRole;
import com.sky.result.PageResult;
import com.sky.vo.BasePageReqVo;
import org.springframework.stereotype.Service;

@Service
public interface RoleService extends IService<SysRole> {
    String getRoleString(SysUser user);

    PageResult getPage(BasePageReqVo basePageReqVo);

    void deleteRole(Long id);
}
