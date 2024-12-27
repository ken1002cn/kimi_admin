package com.kimi.system.interceptor;

import com.kimi.common.constant.JwtClaimsConstant;
import com.kimi.common.constant.JwtParameterConstant;
import com.kimi.common.constant.RoleConstant;
import com.kimi.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }
        //1、从请求头中获取令牌
        String token = request.getHeader(JwtParameterConstant.JWT_TOKEN_NAME);
        //2、校验令牌
        try {
            log.info("jwt校验:{}", token);
            Claims claims = JwtUtil.parseJWT(JwtParameterConstant.JWT_SECRET_KEY, token);
            String role = String.valueOf(claims.get(JwtClaimsConstant.ROLE).toString());
            log.info("当前请求用户权限：{}", role);
            if (role.contains(RoleConstant.ADMIN.toString())) {
                log.info("权限验证通过");
                return true;
            }
            log.info("权限验证失败");
            response.setStatus(402,"没有权限进行此操作");
            return false;
        } catch (Exception ex) {
            //4、不通过，响应状态码401
            response.setStatus(401,"jwt验证不通过");
            return false;
        }
    }
}