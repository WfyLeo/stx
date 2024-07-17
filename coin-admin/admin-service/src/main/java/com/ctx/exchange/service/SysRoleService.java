package com.ctx.exchange.service;

import com.ctx.exchange.domain.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
public interface SysRoleService extends IService<SysRole>{


    boolean isSuperAdmin(long userId);
}
