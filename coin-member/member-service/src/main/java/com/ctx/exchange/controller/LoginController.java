package com.ctx.exchange.controller;

import com.ctx.exchange.model.LoginForm;
import com.ctx.exchange.model.R;
import com.ctx.exchange.service.LoginService;
import com.ctx.exchange.vo.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@Api(tags = "登录的控制器")
public class LoginController {

    @Autowired
    private LoginService loginService ;

    @PostMapping("/login")
    @ApiOperation(value = "会员的登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginForm",value = "登录的表单参数")
    })
    public R<LoginUser> login(@RequestBody @Validated LoginForm loginForm) throws Exception {
        LoginUser loginUser =  loginService.login(loginForm) ;
        return R.ok(loginUser) ;
    }

    public static void main(String[] args) {
        // Updated regex pattern to match img tags more robustly
        String imgTagPattern = "<img\\s+[^>]*src\\s*=\\s*\"[^\"]*\"[^>]*>";

        String text = "<p>所有地区首页公告001所有地区首页公告001所有地区首页公告001\n" +
                "所有地区首页公告001所有地区首页公告001所有地区首页公告001\n" +
                "所有地区首页公告001所有地区首页公告001所有地区首页公告001所有地区首页公告001<img src=\"https://zzz.m1cdn.com/20240728/3d58879b725448f0addade15b7b410a8.jpg\" contenteditable=\"false\" style=\"font-size: 14px; max-width: 100%;\"/>所有地区首页公告001所有地区首页公告001所有地区首页公告001所有地区首页公告001所有地区首页公告001所有地区首页公告001所有地区首页公告001所有地区首页公告001所有地区首页公告001</p>";

        Pattern pattern = Pattern.compile(imgTagPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);

        if (!matcher.find()) {
            System.out.println(11111);
        } else {
            System.out.println(22222);
        }
    }
}
