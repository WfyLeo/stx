package com.ctx.exchange.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctx.exchange.domain.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
public interface SysRoleService extends IService<SysRole>{


    boolean isSuperAdmin(long userId);

    /**
     * 使用角色的名称模糊分页角色查询
     * @param page
     * 分页数据
     * @param name
     *  角色的名称
     * @return
     */
    Page<SysRole> findByPage(Page<SysRole> page, String name);
}
