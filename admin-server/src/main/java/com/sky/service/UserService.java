package com.sky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.entity.SysUser;
import com.sky.result.PageResult;
import com.sky.vo.BasePageReqVo;
import com.sky.vo.LoginRespVo;
import com.sky.vo.UserSaveReqVo;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends IService<SysUser> {
    LoginRespVo login(SysUser user);

    PageResult listUser(BasePageReqVo basePageReqVo);

    void saveUser(UserSaveReqVo user);

    void changeStatus(Long id);

    void deleteUser(String id);
}
