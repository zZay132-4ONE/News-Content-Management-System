package com.github.zzay.handler;

import com.alibaba.fastjson.JSON;
import com.github.zzay.result.ResponseBean;
import com.github.zzay.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zzay
 * @className MyAuthenticationFailureHandler
 * @description 自定义认证失败处理器
 * @create 2022/04/21 00:20
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    /**
     * 当认证失败时触发
     *
     * @param request   请求
     * @param response  响应
     * @param exception 认证失败异常
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(HttpStatus.UNAUTHORIZED.value());
        if (exception instanceof LockedException) {
            responseBean.setMsg("账户被锁定，请联系管理员!");
        } else if (exception instanceof CredentialsExpiredException) {
            responseBean.setMsg("密码过期，请联系管理员!");
        } else if (exception instanceof AccountExpiredException) {
            responseBean.setMsg("账户过期，请联系管理员!");
        } else if (exception instanceof DisabledException) {
            responseBean.setMsg("账户被禁用，请联系管理员!");
        } else if (exception instanceof BadCredentialsException) {
            responseBean.setMsg("用户名或者密码输入错误，请重新输入!");
        }
        String json = JSON.toJSONString(responseBean);
        WebUtils.renderString(response, json);
    }

}
