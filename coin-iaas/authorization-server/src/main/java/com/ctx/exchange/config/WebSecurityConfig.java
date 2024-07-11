package com.ctx.exchange.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {


    /**
     * 注入一个验证管理器
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 所有请求都需要用户进行身份验证，并禁用 CSRF 保护
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().anyRequest().authenticated();
    }

    /**
     * 注入密码的校验管理器
     * 它使用 BCrypt 强哈希函数来加密密码。BCrypt 是一种密码哈希函数，基于 Blowfish 加密算法，通常用于存储密码的安全性要求较高的应用中。
     * BCryptPasswordEncoder 不是对称加密算法，而是一种哈希函数，属于单向加密（或称为单向哈希）算法。
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("123456");
        System.out.printf(encode);
    }
}
