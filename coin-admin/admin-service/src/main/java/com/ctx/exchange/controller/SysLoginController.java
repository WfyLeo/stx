package com.ctx.exchange.controller;

import com.ctx.exchange.model.LoginResult;
import com.ctx.exchange.service.SysLoginService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Api(tags = "登录的控制器")
@RestController
public class SysLoginController {

    @Autowired
    private SysLoginService loginService;

    @PostMapping("/login")
    @ApiOperation(value = "后台管理人员登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名称"),
            @ApiImplicitParam(name = "password",value = "用户密码")
    })
    public LoginResult login(@RequestParam() String username,
                             @RequestParam() String password){
        return loginService.login(username, password);
    }
}
