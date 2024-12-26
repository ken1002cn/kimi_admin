package com.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.constant.JwtClaimsConstant;
import com.sky.constant.JwtParameterConstant;
import com.sky.constant.StatusConstant;
import com.sky.entity.SysDept;
import com.sky.entity.SysRole;
import com.sky.entity.SysUser;
import com.sky.entity.UserRole;
import com.sky.exception.LoginException;
import com.sky.mapper.DeptMapper;
import com.sky.mapper.RoleUserMapper;
import com.sky.mapper.UserMapper;
import com.sky.result.PageResult;
import com.sky.service.RoleService;
import com.sky.service.UserService;
import com.sky.utils.JwtUtil;
import com.sky.vo.BasePageReqVo;
import com.sky.vo.LoginRespVo;
import com.sky.vo.UserListRespVo;
import com.sky.vo.UserSaveReqVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public LoginRespVo login(SysUser user){
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<SysUser>();
        queryWrapper.eq(SysUser::getAccount,user.getAccount());
        queryWrapper.eq(SysUser::getPassword,user.getPassword());
        SysUser sysUser = baseMapper.selectOne(queryWrapper);
        if (sysUser!=null){
            String status = sysUser.getStatus();
            if (status.equals(StatusConstant.ENABLE)){
                //账号密码有效 封装返回数据
                Map<String,Object> claims = new HashMap<>();
                claims.put(JwtClaimsConstant.USER_ID,sysUser.getId());
                claims.put(JwtClaimsConstant.ROLE,getRole(sysUser.getId()));
                String token = JwtUtil.createJWT(JwtParameterConstant.JWT_SECRET_KEY, JwtParameterConstant.JWT_TOKEN_TTL, claims);
                return LoginRespVo.builder()
                        .token(token)
                        .uid(sysUser.getId())
                        .username(sysUser.getNickname())
                        .build();
            }
           throw new RuntimeException("账号未启用");
        }
       throw new LoginException("账号或密码错误");
    }

    private String getRole(Long id) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getUserId,id);
        List<UserRole> userRoles = roleUserMapper.selectList(queryWrapper);
        StringBuilder roles = new StringBuilder();
        userRoles.forEach(userRole -> {
            SysRole byId = roleService.getById(userRole.getRoleId());
            roles.append(byId.getCode()).append(",");
        });
        return roles.toString();
    }

    @Override
    public PageResult listUser(BasePageReqVo basePageReqVo) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        if (basePageReqVo.getQuery()!=null){
            wrapper.like(SysUser::getAccount,basePageReqVo.getQuery());
        }
        IPage<SysUser> iPage = baseMapper.selectPage(new Page<>(basePageReqVo.getPageNum(),basePageReqVo.getPageSize()),wrapper);
        List<SysUser> userDos = iPage.getRecords();
        List<UserListRespVo> userListRespVoList = userDos.stream().map(user -> {
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
                    .id(user.getId())
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
                    .status(user.getStatus())
                    .build();
        }).collect(Collectors.toList());
        return new PageResult(iPage.getTotal(),userListRespVoList);
    }

    @Override
    @Transactional
    public void saveUser(UserSaveReqVo user) {
        SysUser sysUser = SysUser.builder()
                .deptId(user.getDept())
                .password(user.getPassword())
                .account(user.getAccount())
                .sex(user.getSex())
                .email(user.getEmail())
                .phone(user.getPhone())
                .nickname(user.getNickname())
                .createTime(new Date())
                .build();

        String status = user.isStatus()? "启用" : "停用";
        sysUser.setStatus(status);
        sysUser.setCreateTime(new Date());
        baseMapper.insert(sysUser);
        createRelRole(user.getRoles(), sysUser.getId());
    }

    //关联用户角色
    private void createRelRole(Long[] roles,Long userId){
        //清除原有关系
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getUserId,userId);
        roleUserMapper.delete(queryWrapper);

        for (Long role : roles) {
            UserRole userRole = UserRole.builder()
                    .userId(userId)
                    .roleId(role)
                    .build();
            roleUserMapper.insert(userRole);
        }
    }

    @Override
    public void changeStatus(Long id) {
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

    @Override
    public void updateUser(UserSaveReqVo user) {
        SysUser sysUser = SysUser.builder()
                .id(user.getId())
                .deptId(user.getDept())
                .password(user.getPassword())
                .sex(user.getSex())
                .email(user.getEmail())
                .phone(user.getPhone())
                .nickname(user.getNickname())
                .updateTime(new Date())
                .build();
        baseMapper.updateById(sysUser);
    }

    @Override
    public void updateUserRole(UserSaveReqVo user) {
        SysUser sysUser = SysUser.builder()
                .id(user.getId())
                .deptId(user.getDept())
                .build();
        baseMapper.updateById(sysUser);
        createRelRole(user.getRoles(), user.getId());
    }
}
