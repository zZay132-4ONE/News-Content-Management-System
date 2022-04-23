package com.github.zzay.handler;

import com.alibaba.fastjson.JSON;
import com.github.zzay.result.ResponseBean;
import com.github.zzay.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zzay
 * @className AuthenticationEntryPointImpl
 * @description 自定义认证异常处理
 * @create 2022/04/20 23:50
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * 认证异常处理
     * 返回JSON格式，方便前端对响应进行统一的处理
     *
     * @param request       请求
     * @param response      响应
     * @param authException 认证异常
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) {
        ResponseBean result = new ResponseBean(HttpStatus.UNAUTHORIZED.value(), "用户尚未登录，请先登录");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }

}
