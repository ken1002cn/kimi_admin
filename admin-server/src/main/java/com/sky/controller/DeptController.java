package com.sky.controller;

import com.sky.entity.SysDept;
import com.sky.entity.SysRole;
import com.sky.result.Result;
import com.sky.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/dept/")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @PostMapping("create")
    public Result create(@RequestBody SysDept dept) {
        deptService.save(dept);
        return Result.success();
    }
}
