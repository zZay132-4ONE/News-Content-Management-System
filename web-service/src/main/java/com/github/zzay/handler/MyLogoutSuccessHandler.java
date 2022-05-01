package com.github.zzay.handler;

import com.alibaba.fastjson.JSON;
import com.github.zzay.result.ResponseBean;
import com.github.zzay.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zzay
 * @className MyLogoutSuccessHandler
 * @description 自定义登出成功处理器
 * @create 2022/04/21 00:22
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    /**
     * 当登出成功时触发
     *
     * @param request        请求
     * @param response       响应
     * @param authentication 认证信息
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        ResponseBean responseBean = new ResponseBean(HttpStatus.OK.value(), "登出成功");
        String json = JSON.toJSONString(responseBean);
        WebUtils.renderString(response, json);
    }

}
