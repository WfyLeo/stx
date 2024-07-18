package com.ctx.exchange.controller;

import com.ctx.exchange.domain.SysUser;
import com.ctx.exchange.model.R;
import com.ctx.exchange.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "admin-service-test接口")
public class TestController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/user/info/{id}")
    public R<SysUser> getUser(@PathVariable Long id) {
        return R.ok(sysUserService.getById(id));
    }

}
