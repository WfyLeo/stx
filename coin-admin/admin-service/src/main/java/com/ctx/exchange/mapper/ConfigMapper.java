package com.ctx.exchange.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctx.exchange.domain.Config;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConfigMapper extends BaseMapper<Config> {
}