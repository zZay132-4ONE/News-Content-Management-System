package com.github.zzay.handler;

import com.alibaba.fastjson.JSON;
import com.github.zzay.result.ResponseBean;
import com.github.zzay.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zzay
 * @className AccessDeniedHandlerImpl
 * @description 自定义授权异常处理
 * @create 2022/04/20 23:55
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * 自定义授权失败异常处理
     *
     * @param request               请求
     * @param response              响应
     * @param accessDeniedException 授权失败异常
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) {
        ResponseBean result = new ResponseBean(HttpStatus.FORBIDDEN.value(), "当前用户权限不足");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }

}
