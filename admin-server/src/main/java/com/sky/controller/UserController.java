package com.sky.controller;

import com.sky.entity.SysUser;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.UserService;
import com.sky.vo.BasePageReqVo;
import com.sky.vo.LoginRespVo;
import com.sky.vo.UserSaveReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user/")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public Result login(@RequestBody SysUser user) {
        LoginRespVo loginRespVo = userService.login(user);
        if (loginRespVo != null) {
            return Result.success(loginRespVo);
        }
        return Result.error("账号密码不正确",500);
    }

    @PostMapping("list")
    public Result list(@RequestBody BasePageReqVo basePageReqVo) {
        PageResult pageResult = userService.listUser(basePageReqVo);
        return Result.success(pageResult);
    }

    @PostMapping("create")
    public Result create(@RequestBody UserSaveReqVo user) {
        userService.saveUser(user);
        return Result.success();
    }
    @GetMapping("delete/{id}")
    public Result delete(@PathVariable("id") String id) {
        userService.deleteUser(id);
        return Result.success();
    }
    @GetMapping("status/{id}")
    public Result status(@PathVariable Long id) {
        userService.changeStatus(id);
        return Result.success();
    }
}
