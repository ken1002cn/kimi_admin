package com.sky.controller;

import com.sky.entity.SysUser;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.UserService;
import com.sky.vo.BasePageReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public Result login(@RequestBody SysUser user) {
        if(user != null) {
            String token = userService.login(user);
            if(token != null) {
                return Result.success(token);
            }
            return Result.error("账号密码不正确",500);
        }
        return Result.error("登录信息传递不正确",401);
    }

    @PostMapping("list")
    public Result list(@RequestBody BasePageReqVo basePageReqVo) {
        PageResult pageResult = userService.listUser(basePageReqVo);
        return Result.success(pageResult);
    }

    @PostMapping("create")
    public Result create(@RequestBody SysUser user) {
        userService.save(user);
        return Result.success();
    }
}
