package com.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.constant.JwtClaimsConstant;
import com.sky.constant.JwtParameterConstant;
import com.sky.entity.SysUser;
import com.sky.mapper.UserMapper;
import com.sky.result.PageResult;
import com.sky.service.UserService;
import com.sky.utils.JwtUtil;
import com.sky.vo.BasePageReqVo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements UserService {

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
        return new PageResult(iPage.getTotal(),iPage.getRecords());
    }
}
