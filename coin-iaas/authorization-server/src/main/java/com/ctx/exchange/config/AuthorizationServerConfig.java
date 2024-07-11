package com.ctx.exchange.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("userServiceDetailServiceImpl")
    private UserDetailsService userDetailsService;


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("coin-api")//第三方客户端的名称
                .secret(passwordEncoder.encode("coin-secret"))//第三方客户端的秘钥
                //授权方式配置，具体来说是关于授权码模式（authorization_code）、密码模式（password）和刷新令牌（refresh_token）的配置。
                .authorizedGrantTypes("authorization_code", "password", "refresh_token")
                .scopes("all")//第三方客户端的收全方位
                .accessTokenValiditySeconds(3600 * 7 * 24)//token的有效期 7天
                .refreshTokenValiditySeconds(30 * 24 * 3600)//refresh_token有效期 30天
                .and()
                .withClient("inside-app")//服务之间调用的客户端
                .secret(passwordEncoder.encode("inside-secret"))
                .scopes("all")
                .authorizedGrantTypes("client_credentials")
                .accessTokenValiditySeconds(3600 * 7 * 24);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(jwtToken())
                .tokenEnhancer(jwtAccessTokenConverter())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    /**
     * jwt token
     * @return
     */
    private TokenStore jwtToken(){
        JwtTokenStore tokenStore = new JwtTokenStore(jwtAccessTokenConverter());
        return tokenStore;
    }

    /**
     *
     * @return
     */
    private JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //读取classPathResource下面的文件
        ClassPathResource pathResource = new ClassPathResource("coinexchange.jks");
        //读取keyStareFactiry
        KeyStoreKeyFactory factory = new KeyStoreKeyFactory(pathResource,"coinexchange".toCharArray());
        converter.setKeyPair(factory.getKeyPair("coinexchange","coinexchange".toCharArray()));
        return converter;
    }
}
