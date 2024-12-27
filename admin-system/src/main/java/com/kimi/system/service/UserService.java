package com.kimi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.kimi.common.pojo.entity.SysUser;
import com.kimi.common.pojo.vo.BasePageReqVo;
import com.kimi.common.pojo.vo.LoginRespVo;
import com.kimi.common.pojo.vo.UserSaveReqVo;
import com.kimi.common.result.PageResult;

import org.springframework.stereotype.Service;

@Service
public interface UserService extends IService<SysUser> {
    LoginRespVo login(SysUser user);

    PageResult listUser(BasePageReqVo basePageReqVo);

    void saveUser(UserSaveReqVo user);

    void changeStatus(Long id);

    void deleteUser(String id);

    void updateUser(UserSaveReqVo user);

    void updateUserRole(UserSaveReqVo user);
}
