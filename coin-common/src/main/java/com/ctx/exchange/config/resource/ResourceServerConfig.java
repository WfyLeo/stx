package com.ctx.exchange.config.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

@Slf4j
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        /**
         * CSRF（Cross-Site Request Forgery，跨站请求伪造）是一种网络攻击，它通过伪造用户请求，诱骗用户在已认证的状态下执行非本意的操作。攻击者通常利用受害者的身份验证凭证（如 Cookie）在受害者不知情的情况下向受信任的网站发送恶意请求。
         */
        http.csrf().disable()// 禁用 CSRF 保护，因为使用了 JWT，不需要 CSRFf
                .sessionManagement().disable()//禁用基于会话的管理，使用 Token 进行身份验证
                .authorizeRequests().antMatchers(
                        "/login",
                        "/v2/api-docs",
                        "/swagger-resources/configuration/ui",//用来获取支持的动作
                        "/swagger-resources",//用来获取api-docs的URI
                        "/swagger-resources/configuration/security",//安全选项
                        "/webjars/**",
                        "/swagger-ui.html"
                ).permitAll()// 允许上述路径的所有请求
                .antMatchers("/**").authenticated()// 其他所有请求都需要认证
                .and().headers().cacheControl();// 禁用缓存控制
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore());
    }

    private TokenStore tokenStore() {
        JwtTokenStore tokenStore = new JwtTokenStore(jwtAccessTokenConverter());
        return tokenStore;
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //读取classPathResource下面的文件
        ClassPathResource pathResource = new ClassPathResource("coinexchange-pub.txt");
        String publicKey = null;
        try {
            byte[] bytes = FileCopyUtils.copyToByteArray(pathResource.getInputStream());
            publicKey = new String(bytes);
        }catch (IOException e) {
            log.info("读取公钥失败");
        }
        converter.setVerifierKey(publicKey);
        return converter;
    }
}
