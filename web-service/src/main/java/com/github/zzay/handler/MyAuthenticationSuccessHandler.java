package com.github.zzay.handler;

import com.alibaba.fastjson.JSON;
import com.github.zzay.result.ResponseBean;
import com.github.zzay.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zzay
 * @className MySuccessHandler
 * @description 自定义认证成功处理器
 * @create 2022/04/21 00:13
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * 当认证成功时触发
     *
     * @param request        请求
     * @param response       响应
     * @param authentication 认证信息
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) {
        ResponseBean responseBean = new ResponseBean(HttpStatus.OK.value(), "认证成功");
        String json = JSON.toJSONString(responseBean);
        WebUtils.renderString(response, json);
    }

}
