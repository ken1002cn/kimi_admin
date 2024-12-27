package com.kimi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.kimi.common.pojo.entity.SysDept;
import com.kimi.common.pojo.entity.SysRole;
import com.kimi.common.pojo.entity.SysUser;
import com.kimi.common.pojo.entity.UserRole;
import com.kimi.common.pojo.vo.BasePageReqVo;
import com.kimi.common.pojo.vo.DeptRespVo;
import com.kimi.system.mapper.DeptMapper;
import com.kimi.system.mapper.RoleMapper;
import com.kimi.system.mapper.RoleUserMapper;
import com.kimi.system.mapper.UserMapper;
import com.kimi.common.result.PageResult;
import com.kimi.system.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, SysDept> implements DeptService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleUserMapper roleUserMapper;

    @Transactional
    @Override
    public void deleteDept(Long id) {
        //递归清除所有id为传入id 父id为传入id的组织以及这些组织的权限
        deleteDeptRel(id);
    }
    private void deleteDeptRel(Long id) {
        //查询id，或父id等于传入id的组织
        LambdaQueryWrapper<SysDept> deptLambdaQueryWrapper = new LambdaQueryWrapper<>();
        deptLambdaQueryWrapper.eq(SysDept::getId, id).or().eq(SysDept::getParentId, id);
        List<SysDept> sysDepts = baseMapper.selectList(deptLambdaQueryWrapper);
        //退出递归
        if (sysDepts == null || sysDepts.isEmpty()) {
            return;
        }
        sysDepts.forEach(sysDept -> {
            //更新用户
            LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<SysUser>();
            queryWrapper.eq(SysUser::getDeptId, sysDept.getId());
            List<SysUser> sysUsers = userMapper.selectList(queryWrapper);
            sysUsers.forEach(sysUser -> {
                sysUser.setDeptId(null);
                userMapper.updateById(sysUser);
            });
            //查出这个被删除的部门的权限
            LambdaQueryWrapper<SysRole> roleLambdaQueryWrapper = new LambdaQueryWrapper<>();
            roleLambdaQueryWrapper.eq(SysRole::getDeptId, sysDept.getId());
            List<SysRole> sysRoles = roleMapper.selectList(roleLambdaQueryWrapper);
            sysRoles.forEach(sysRole -> {
                //删除权限
                roleMapper.deleteById(sysRole.getId());
                //删除权限关联
                LambdaQueryWrapper<UserRole> userRoleLambdaQueryWrapper = new LambdaQueryWrapper<>();
                userRoleLambdaQueryWrapper.eq(UserRole::getRoleId, sysRole.getId());
                roleUserMapper.delete(userRoleLambdaQueryWrapper);
            });
            //删除部门自身
            baseMapper.deleteById(sysDept.getId());
            deleteDeptRel(sysDept.getId());
        });
    }

    @Override
    public PageResult listDept(BasePageReqVo pageReqVo) {
        IPage<SysDept> iPage = baseMapper.selectPage(new Page<>(pageReqVo.getPageNum(),pageReqVo.getPageSize()),new QueryWrapper<SysDept>());
        if (iPage.getRecords().isEmpty()) {
            return new PageResult(iPage.getTotal(),iPage.getRecords());
        }
        //封装vo
        List<SysDept> records = iPage.getRecords();
        List<DeptRespVo> voList = records.stream().map(item -> {
            String deptName = "";
            if (item.getParentId()!=null){
                deptName = baseMapper.selectById(item.getParentId()).getDeptName();
            }

            return DeptRespVo.builder().id(item.getId()).deptName(item.getDeptName()).parentDept(deptName).createTime(item.getCreateTime()).build();
        }).collect(Collectors.toList());
        return new PageResult(iPage.getTotal(),voList);
    }
}
