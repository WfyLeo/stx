package com.ctx.exchange.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctx.exchange.domain.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<SysMenu> getMenuByUserId(long userId);
}