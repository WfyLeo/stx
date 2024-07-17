package com.ctx.exchange.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.enums.ApiErrorCode;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.ctx.exchange.domain.SysMenu;
import com.ctx.exchange.feign.JwtToken;
import com.ctx.exchange.feign.OAuth2FeignClient;
import com.ctx.exchange.model.LoginResult;
import com.ctx.exchange.service.SysLoginService;
import com.ctx.exchange.service.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SysLoginServiceImpl implements SysLoginService {

    @Autowired
    private OAuth2FeignClient oAuth2FeignClient;

    @Autowired
    private SysMenuService sysMenuService;

    @Value("${basic.token:Basic Y29pbi1hcGk6Y29pbi1zZWNyZXQ=}")
    private String basicToken;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public LoginResult login(String username, String password) {
        log.info("用户{}开始登录",username);
        //获取token，需要远程调用authorization-server服务
        ResponseEntity<JwtToken> token = oAuth2FeignClient.getToken("password", username, password, "admin_type", basicToken);
        if(token.getStatusCode() != HttpStatus.OK){
            throw new ApiException(ApiErrorCode.FAILED);
        }
        JwtToken jwtToken = token.getBody();
        log.info("远程调用服务器成功，jwtToken is {}",jwtToken);
        String accessToken = jwtToken.getAccessToken();
        //解析token
        Jwt jwt = JwtHelper.decode(accessToken);
        String jwtClaims = jwt.getClaims();
        JSONObject jsonObject = JSON.parseObject(jwtClaims);
        long userId = Long.parseLong(jsonObject.get("user_name").toString());
        List<SysMenu> sysMenuList = sysMenuService.getMenuByUserId(userId);
        //获取权限
        JSONArray authoritiesJsonArray = jsonObject.getJSONArray("authorities");
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = authoritiesJsonArray.stream().map(authority -> new SimpleGrantedAuthority(authority.toString())).collect(Collectors.toList());
        redisTemplate.opsForValue().set(accessToken,"",jwtToken.getExpiresIn(), TimeUnit.SECONDS);
        return new LoginResult(jwtToken.getTokenType() + " " + accessToken,sysMenuList,simpleGrantedAuthorities);
    }
}
