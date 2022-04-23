package com.github.zzay.filter;

import com.github.zzay.entity.LoginUser;
import com.github.zzay.utils.JwtUtils;
import com.github.zzay.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author zzay
 * @className JwtAuthenticationTokenFilter
 * @description JWT认证过滤器
 * @create 2022/04/21 21:33
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    /**
     * Redis缓存实例，用于获取对应的用户信息并校验（在登录时，已将用户信息存入到redis中）
     */
    @Autowired
    private RedisCache redisCache;

    /**
     * 认证逻辑
     *
     * @param request     请求
     * @param response    响应
     * @param filterChain filter chain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 获取传入请求头中的token信息
        String token = request.getHeader("token");
        // 若token为空，说明仍未登录，放行
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        // 利用JWT工具类解析token，获得userId
        String userId;
        try {
            Claims claims = JwtUtils.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        // 从redis中获取登录时存入的对应用户信息
        String redisKey = "login:" + userId;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("用户未登录");
        }
        // 将当前用户的校验信息封装到Authentication中，
        // 并存入到SecurityContext中，方便后续的filters进行认证
        // 注意，这个构造器会将authenticated设置为true，即默认通过验证（credentials传入null，以及需要authorities的原因）
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(), null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 放行
        filterChain.doFilter(request, response);
    }

}
