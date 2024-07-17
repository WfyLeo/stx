package com.ctx.exchange.service.impl;

import com.ctx.exchange.mapper.SysRoleMapper;
import com.ctx.exchange.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctx.exchange.mapper.SysMenuMapper;
import com.ctx.exchange.domain.SysMenu;
import com.ctx.exchange.service.SysMenuService;
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService{


    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleService sysRoleService;

    @Override
    public List<SysMenu> getMenuByUserId(long userId) {
        if(sysRoleService.isSuperAdmin(userId)){
            return list();
        }
        return sysMenuMapper.getMenuByUserId(userId);
    }
}
