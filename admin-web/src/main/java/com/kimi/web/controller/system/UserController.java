package com.kimi.web.controller.system;


import com.kimi.common.pojo.entity.SysUser;
import com.kimi.common.pojo.vo.BasePageReqVo;
import com.kimi.common.pojo.vo.LoginRespVo;
import com.kimi.common.pojo.vo.UserSaveReqVo;
import com.kimi.common.result.PageResult;
import com.kimi.common.result.Result;
import com.kimi.system.service.UserService;
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

    @PostMapping("update")
    public Result update(@RequestBody UserSaveReqVo user) {
        userService.updateUser(user);
        return Result.success();
    }

    @PostMapping("updateRole")
    public Result updateRole(@RequestBody UserSaveReqVo user) {
        userService.updateUserRole(user);
        return Result.success();
    }
}
