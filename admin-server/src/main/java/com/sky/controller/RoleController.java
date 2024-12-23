package com.sky.controller;

import com.sky.entity.SysRole;
import com.sky.result.Result;
import com.sky.service.RoleService;
import com.sky.vo.BasePageReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/role/")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping("list")
    public Result list(BasePageReqVo basePageReqVo) {
        return Result.success(roleService.getPage(basePageReqVo));
    }
    @PostMapping("create")
    public Result create(@RequestBody SysRole role) {
        roleService.save(role);
        return Result.success();
    }
    @GetMapping("delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        roleService.deleteRole(id);
        return Result.success();
    }
}
