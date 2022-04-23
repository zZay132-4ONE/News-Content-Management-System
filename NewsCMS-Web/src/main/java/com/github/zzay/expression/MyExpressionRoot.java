package com.github.zzay.expression;

import com.github.zzay.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zzay
 * @className MyExpressionRoot
 * @description 自定义权限校验逻辑
 * @create 2022/04/22 20:33
 */
@Component("myExpr")
public class MyExpressionRoot {

    /**
     * 校验权限
     *
     * @param authority 所指定的权限
     * @return 用户是否具有所指定的权限
     */
    public boolean hasAuthority(String authority) {
        // Get the "Authentication" of the current user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();
        // Judge whether the current user has the expected authority
        return permissions.contains(authority);
    }

}
