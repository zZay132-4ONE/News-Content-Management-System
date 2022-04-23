package com.github.zzay.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 实现UserDetails接口，目的是封装对应的用户信息及权限信息，
 * 并在自定义的UserDetailsService中的"loadByUsername"方法中返回给AuthenticationProvider校验
 *
 * @author zzay
 * @className LoginUser
 * @description 封装UserDetails
 * @create 2022/04/21 17:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements UserDetails {

    /**
     * 用户
     */
    private User user;

    /**
     * 登录的用户拥有的权限列表（String类型）
     */
    private List<String> permissions;

    /**
     * 登录的用户拥有的权限列表（封装为SimpleGrantedAuthority类型），
     * 原因是会默认调用UserDetails的getAuthorities()方法，其类型是"? extends GrantedAuthority"
     * <p>
     * 注意，不需要被传输，因此将其"@JSONField"设置为不需要被序列化
     */
    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;

    /**
     * 初始化登录的用户信息
     *
     * @param user        用户
     * @param permissions 登录的用户拥有的权限列表
     */
    public LoginUser(User user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    /**
     * 返回当前用户所拥有的权限列表
     *
     * @return 当前用户所拥有的权限列表
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities != null) {
            return authorities;
        }
        // 把permissions中String类型的权限信息封装成SimpleGrantedAuthority对象
//        authorities = new ArrayList<>();
//        for (String permission : permissions) {
//            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
//            authorities.add(authority);
//        }
        // 利用函数式编程写法
        authorities = permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return authorities;
    }

    /**
     * 返回该用户的密码
     *
     * @return 密码
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * 返回该用户的用户名
     *
     * @return 用户名
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * 判断当前账号是否有效
     *
     * @return 当前账号是否有效
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 判断当前账号是否未被锁定
     *
     * @return 当前账号是否未被锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 判断当前用户的凭证是否仍有效
     *
     * @return 当前用户的凭证是否仍有效
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 判断当前账号是否被启用
     *
     * @return 当前账号是否被启用
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

}
