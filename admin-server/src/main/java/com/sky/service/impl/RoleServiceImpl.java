package com.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.entity.SysRole;
import com.sky.entity.SysUser;
import com.sky.entity.UserRole;
import com.sky.mapper.RoleMapper;
import com.sky.mapper.RoleUserMapper;
import com.sky.result.PageResult;
import com.sky.service.RoleService;
import com.sky.vo.BasePageReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, SysRole> implements RoleService {
    @Autowired
    private RoleUserMapper roleUserMapper;

    /**
     * 拼接权限字符串
     * @param user
     * @return
     */
    @Override
    public String getRoleString(SysUser user) {
        LambdaQueryWrapper<UserRole> userRoleWrapper = new LambdaQueryWrapper<>();
        userRoleWrapper.eq(UserRole::getUserId, user.getId());
        List<UserRole> userRoles = roleUserMapper.selectList(userRoleWrapper);
        StringBuilder roleString = new StringBuilder();
        userRoles.forEach(userRole -> {
            LambdaQueryWrapper<SysRole> sysRoleWrapper = new LambdaQueryWrapper<>();
            sysRoleWrapper.eq(SysRole::getId, userRole.getRoleId());
            SysRole sysRole = baseMapper.selectOne(sysRoleWrapper);
            roleString.append(sysRole.getRoleName()).append(",");
        });
        return roleString.toString();
    }

    @Override
    public PageResult getPage(BasePageReqVo basePageReqVo) {
        IPage<SysRole> sysRolePage = baseMapper.selectPage(new Page<>(basePageReqVo.getPageNum(), basePageReqVo.getPageSize()), new QueryWrapper<SysRole>());
        return new PageResult(sysRolePage.getTotal(),sysRolePage.getRecords());
    }

    @Override
    public void deleteRole(Long id) {

    }
}
