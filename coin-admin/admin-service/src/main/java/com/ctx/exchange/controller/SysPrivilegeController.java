package com.ctx.exchange.controller;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctx.exchange.domain.SysPrivilege;
import com.ctx.exchange.model.R;
import com.ctx.exchange.service.SysPrivilegeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;

/**
 * sys_privilege_delete
 * sys_privilege_update
 * sys_privilege_create
 * sys_privilege_query
 */

@RequestMapping("/privileges")
@RestController
@Api(tags = "权限的管理")
public class SysPrivilegeController {

    @Autowired
    private SysPrivilegeService sysPrivilegeService;


    /**
     * 分页查询   @ApiIgnore 忽略
     * @param page
     * @return
     */
    @GetMapping
    @ApiOperation(value = "分页查询权限数据")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "current",value = "当前页"),
                    @ApiImplicitParam(name = "size",value = "每页显示的大小")
            }
    )
    @PreAuthorize("hasAnyAuthority('sys_privilege_query')")//权限验证
    public R<Page<SysPrivilege>> findByPage(@ApiIgnore Page<SysPrivilege> page){
        page.addOrder(OrderItem.desc("last_update_time"));
        Page<SysPrivilege> sysPrivilegePage = sysPrivilegeService.page(page);
        return R.ok(sysPrivilegePage);
    }

    @PostMapping
    @ApiOperation(value = "新增权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysPrivilege",value = "sysPrivilege的json数据")
    })
    @PreAuthorize("hasAnyAuthority('sys_privilege_create')")
    public R add(@RequestBody @Validated SysPrivilege sysPrivilege){
        Boolean save = sysPrivilegeService.save(sysPrivilege);
        if(save){
            return R.ok("新增成功");
        }
        return R.fail("新增失败");
    }

    @PatchMapping
    @ApiOperation("更改权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysPrivilege",value = "sysPrivilege的json数据")
    })
    @PreAuthorize("hasAnyAuthority('sys_privilege_update')")
    public R update(@RequestBody @Validated SysPrivilege sysPrivilege){
        boolean update = sysPrivilegeService.updateById(sysPrivilege);
        if(update){
            return R.ok("更改成功");
        }
        return R.fail("更改失败");
    }
}
