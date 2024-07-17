package com.ctx.exchange.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctx.exchange.domain.SysRole;
import com.ctx.exchange.mapper.SysRoleMapper;
import com.ctx.exchange.service.SysRoleService;
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService{


    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public boolean isSuperAdmin(long userId) {
        String roleCode = sysRoleMapper.getUserRoleCode(userId);
        return StringUtils.isNotBlank(roleCode) && "ROLE_ADMIN".equals(roleCode);
    }
}
