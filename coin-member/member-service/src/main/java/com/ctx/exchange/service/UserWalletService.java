package com.ctx.exchange.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctx.exchange.domain.UserWallet;
import com.baomidou.mybatisplus.extension.service.IService;
public interface UserWalletService extends IService<UserWallet>{


    /**
     * 分页查询用户的提币地址
     * @param page
     * 分页参数
     * @param userId
     * 用户的ID
     * @return
     */
    Page<UserWallet> findByPage(Page<UserWallet> page, Long userId);
}
