package com.sky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.entity.SysUser;
import com.sky.result.PageResult;
import com.sky.vo.BasePageReqVo;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends IService<SysUser> {
    String login(SysUser user);

    PageResult listUser(BasePageReqVo basePageReqVo);
}
