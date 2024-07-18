package com.ctx.exchange.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctx.exchange.domain.SysPrivilege;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface SysPrivilegeMapper extends BaseMapper<SysPrivilege> {

    /**
     * 使用角色Id 查询权限
     * @param roleId
     * @return
     */
    Set<Long> getPrivilegesByRoleId(Long roleId);
}