package com.ctx.exchange.service;

import com.ctx.exchange.domain.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SysMenuService extends IService<SysMenu>{


    List<SysMenu> getMenuByUserId(long userId);
}
