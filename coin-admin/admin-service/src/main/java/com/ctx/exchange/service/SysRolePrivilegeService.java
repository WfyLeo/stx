package com.ctx.exchange.service;

import com.ctx.exchange.domain.SysMenu;
import com.ctx.exchange.domain.SysRolePrivilege;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ctx.exchange.model.RolePrivilegesParam;

import java.util.List;

public interface SysRolePrivilegeService extends IService<SysRolePrivilege>{

    /**
     * 查询角色的权限
     * @param roleId
     * @return
     */
    List<SysMenu> findSysMenuAndPrivileges(Long roleId);

    /**
     * 给角色授权权限
     * @param rolePrivilegesParam
     * @return
     */
    boolean grantPrivileges(RolePrivilegesParam rolePrivilegesParam);
}
