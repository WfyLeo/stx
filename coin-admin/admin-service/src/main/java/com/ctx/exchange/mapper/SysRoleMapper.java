package com.ctx.exchange.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctx.exchange.domain.SysRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    String getUserRoleCode(long userId);
}