package com.ctx.exchange.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctx.exchange.domain.WebConfig;
import com.baomidou.mybatisplus.extension.service.IService;
public interface WebConfigService extends IService<WebConfig>{


    /**
     * 条件分页查询
     * @param page
     *  分页参数
     * @param name
     * webConfig的名称
     * @param type
     * webConfig的类型
     * @return
     */
    Page<WebConfig> findByPage(Page<WebConfig> page, String name, String type);

}
