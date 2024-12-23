package com.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.entity.SysDept;
import com.sky.entity.SysUser;
import com.sky.mapper.DeptMapper;
import com.sky.mapper.UserMapper;
import com.sky.result.PageResult;
import com.sky.service.DeptService;
import com.sky.service.UserService;
import com.sky.vo.BasePageReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, SysDept> implements DeptService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public String deleteDept(Long id) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<SysUser>();
        queryWrapper.eq(SysUser::getDeptId, id);
        List<SysUser> sysUsers = userMapper.selectList(queryWrapper);
        if (sysUsers!=null && !sysUsers.isEmpty()) {
            return "当前已关联到用户，无法删除";
        }
        baseMapper.deleteById(id);
        return "删除成功";
    }

    @Override
    public PageResult listDept(BasePageReqVo pageReqVo) {
        IPage<SysDept> iPage = baseMapper.selectPage(new Page<>(pageReqVo.getPageNum(),pageReqVo.getPageSize()),new QueryWrapper<SysDept>());
        return new PageResult(iPage.getTotal(),iPage.getRecords());
    }
}
