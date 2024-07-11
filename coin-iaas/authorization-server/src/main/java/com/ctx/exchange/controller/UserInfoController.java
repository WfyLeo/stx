package com.ctx.exchange.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserInfoController {


    /**
     * 当前登录的用户对象
     * @param Principal
     * @return
     */
    @GetMapping("/user/info")
    public Principal getPrincipal(Principal Principal){
        return Principal;
    }
}
