package com.ctx.exchange.controller;

import com.ctx.exchange.domain.SysMenu;
import com.ctx.exchange.model.R;
import com.ctx.exchange.model.RolePrivilegesParam;
import com.ctx.exchange.service.SysRolePrivilegeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "角色权限的配置")
public class SysRolePrivilegeController {

    @Autowired
    private SysRolePrivilegeService sysRolePrivilegeService ;

    @GetMapping("/roles_privileges")
    @ApiOperation(value = "查询角色的权限列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId" ,value = "角色的ID")
    })
    public R<List<SysMenu>> findSysMenuAndPrivileges(Long roleId){
        List<SysMenu> sysMenus  = sysRolePrivilegeService.findSysMenuAndPrivileges(roleId) ;
        return R.ok(sysMenus) ;
    }

    @PostMapping("/grant_privileges")
    @ApiOperation(value = "授予角色某种权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rolePrivilegesParam" ,value = "rolePrivilegesParam json数")
    })
    public R grantPrivileges(@RequestBody RolePrivilegesParam rolePrivilegesParam){
        boolean isOk = sysRolePrivilegeService.grantPrivileges(rolePrivilegesParam)  ;
        if(isOk){
            return R.ok() ;
        }
        return R.fail("授予失败") ;
    }
}
