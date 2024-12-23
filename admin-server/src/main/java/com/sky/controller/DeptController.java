package com.sky.controller;

import com.sky.entity.SysDept;
import com.sky.entity.SysRole;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DeptService;
import com.sky.vo.BasePageReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("delete/{id}")
    public Result delete(@PathVariable Long id) {
        return Result.success(deptService.deleteDept(id));
    }

    @PostMapping("list")
    private Result list(@RequestBody BasePageReqVo pageReqVo) {
        PageResult pageResult = deptService.listDept(pageReqVo);
        return Result.success(pageResult);
    }
}
