package com.ctx.exchange.controller;

import com.ctx.exchange.model.LoginResult;
import com.ctx.exchange.service.SysLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "登录的控制器")
@RestController
public class SysLoginController {

    @Autowired
    private SysLoginService loginService;

    @PostMapping("/login")
    @ApiModelProperty(value = "后台管理人员登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名称"),
            @ApiImplicitParam(name = "password",value = "用户密码")
    })
    public LoginResult login(@RequestParam() String username,
                             @RequestParam() String password){
        return loginService.login(username,password);
    }
}
