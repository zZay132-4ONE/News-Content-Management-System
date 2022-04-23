package com.github.zzay.config;

import com.github.zzay.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author zzay
 * @className SecurityConfig
 * @description SpringSecurity配置
 * @create 2022/04/21 14:37
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Authentication白名单
     */
    private static final String[] AUTH_WHITELIST = {
            "/",
            "/*.html",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js",
            "/favicon.ico",
            "/swagger-ui.html",
            "/swagger-ui/",
            "/swagger-ui/*",
            "/swagger-resources/**",
            "/**/api-docs/**",
            "/webjars/**",
    };

    /**
     * 自定义UserDetailsService
     */
    @Autowired
    @Qualifier("UserDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    /**
     * 自定义JWT认证过滤器
     */
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    /**
     * 自定义认证失败处理器
     */
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    /**
     * 自定义授权失败处理器
     */
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    /**
     * 配置HttpSecurity
     *
     * @param http HttpSecurity
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Cross-site settings
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                .and()
//                .formLogin()
//                .loginPage("/login.html")
//                .loginProcessingUrl("/user/login")
//                .permitAll()
        // Authorizations
        http.authorizeRequests()
                .antMatchers("/hello").permitAll()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/user/login").anonymous()
                .anyRequest().authenticated();
        // Filters
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // Exception handlers
        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
        // CORS
        http.cors();
    }

    /**
     * 校验相关配置
     *
     * @param auth AuthenticationManagerBuilder
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    /**
     * 自定义AuthenticationProvider（#loadByUsername）
     *
     * @return AuthenticationProvider
     */
    public AuthenticationProvider authenticationProvider() {
        // Configure customized "UserDetailsService"
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        // Configure password encoder
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * 选择密码加密器并注入到容器
     *
     * @return BCryptPasswordEncoder
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 将AuthenticationManager注入容器
     *
     * @return AuthenticationManager
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 设置权限级别
     *
     * @return RoleHierarchy
     */
    @Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_admin > ROLE_user");
        return roleHierarchy;
    }

}
