package com.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.constant.JwtClaimsConstant;
import com.sky.constant.JwtParameterConstant;
import com.sky.constant.StatusConstant;
import com.sky.entity.SysDept;
import com.sky.entity.SysUser;
import com.sky.entity.UserRole;
import com.sky.mapper.DeptMapper;
import com.sky.mapper.RoleUserMapper;
import com.sky.mapper.UserMapper;
import com.sky.result.PageResult;
import com.sky.service.RoleService;
import com.sky.service.UserService;
import com.sky.utils.JwtUtil;
import com.sky.vo.BasePageReqVo;
import com.sky.vo.UserListRespVo;
import com.sky.vo.UserSaveReqVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements UserService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleUserMapper roleUserMapper;
    @Override
    public String login(SysUser user) {
        if (user.getPassword()!=null && user.getAccount()!=null){
            LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<SysUser>();
            queryWrapper.eq(SysUser::getAccount,user.getAccount());
            queryWrapper.eq(SysUser::getPassword,user.getPassword());
            SysUser sysUser = baseMapper.selectOne(queryWrapper);
            if (sysUser!=null){
                Map<String,Object> claims = new HashMap<>();
                claims.put(JwtClaimsConstant.USER_ID,sysUser.getId());
                return JwtUtil.createJWT(JwtParameterConstant.JWT_SECRET_KEY, JwtParameterConstant.JWT_TOKEN_TTL, claims);
            }
            return null;
        }
        throw new RuntimeException("账号密码未传递");
    }

    @Override
    public PageResult listUser(BasePageReqVo basePageReqVo) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        if (basePageReqVo.getQuery()!=null){
            wrapper.like(SysUser::getAccount,basePageReqVo.getQuery());
        }
        IPage<SysUser> iPage = baseMapper.selectPage(new Page<>(basePageReqVo.getPageNum(),basePageReqVo.getPageSize()),wrapper);
        List<SysUser> userDos = iPage.getRecords();
        List<UserListRespVo.UserListRespVoBuilder> userListRespVoList = userDos.stream().map(user -> {
            //查询权限组
            String roles = roleService.getRoleString(user);
            String deptName = "";
            if (user.getDeptId()!=null){
                //查询公司名
                LambdaQueryWrapper<SysDept> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(SysDept::getId, user.getDeptId());
                SysDept sysDept = deptMapper.selectOne(queryWrapper);
                if (sysDept!=null){
                    deptName = sysDept.getDeptName();
                }
            }
            return UserListRespVo.builder()
                    .account(user.getAccount())
                    .nickname(user.getNickname())
                    .avatar(user.getAvatar())
                    .phone(user.getPhone())
                    .sex(user.getSex())
                    .roles(roles)
                    .deptName(deptName)
                    .createTime(user.getCreateTime())
                    .password(user.getPassword())
                    .email(user.getEmail())
                    .status(user.getStatus());

        }).collect(Collectors.toList());
        return new PageResult(iPage.getTotal(),userListRespVoList);
    }

    @Override
    public void saveUser(UserSaveReqVo user) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(user,sysUser);
        sysUser.setCreateTime(new Date());
        baseMapper.insert(sysUser);
        String roles = user.getRoles();
        String[] role = roles.split(",");
        for (String s : role) {
            UserRole userRole = UserRole.builder()
                    .userId(sysUser.getId())
                    .roleId(Long.valueOf(s))
                    .build();
        roleUserMapper.insert(userRole);
        }
    }

    @Override
    public void changeStatus(String id) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getId,id);
        SysUser sysUser = baseMapper.selectOne(wrapper);
        String status = sysUser.getStatus();
        if (Objects.equals(status, StatusConstant.ENABLE)){
            sysUser.setStatus(StatusConstant.DISABLE);
        }else {
            sysUser.setStatus(StatusConstant.ENABLE);
        }
        sysUser.setUpdateTime(new Date());
        baseMapper.updateById(sysUser);
    }

    @Override
    public void deleteUser(String id) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getId,id);
        baseMapper.delete(wrapper);
        //删除关联记录
        LambdaQueryWrapper<UserRole> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(UserRole::getUserId,id);
        roleUserMapper.delete(wrapper1);
    }
}
