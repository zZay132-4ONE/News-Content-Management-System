package com.github.zzay.handler;

import com.alibaba.fastjson.JSON;
import com.github.zzay.result.ResponseBean;
import com.github.zzay.utils.WebUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
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
@Tag(name = "MyAuthenticationEntryPoint", description = "自定义认证异常处理接口")
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
    @Operation(summary = "认证异常处理", description = "用户在尚未登录的情况下，访问需要权限的页面时执行该操作", security = @SecurityRequirement(name = HttpHeaders.AUTHORIZATION))
    @ApiResponses(value = {@ApiResponse(responseCode = "401", description = "用户尚未登录，请先登录")})
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) {
        ResponseBean result = new ResponseBean(HttpStatus.UNAUTHORIZED.value(), "用户尚未登录，请先登录");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }

}
