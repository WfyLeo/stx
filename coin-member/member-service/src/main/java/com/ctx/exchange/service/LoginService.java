package com.ctx.exchange.service;

import com.ctx.exchange.model.LoginForm;
import com.ctx.exchange.vo.LoginUser;

public interface LoginService {

    /**
     * 会员的登录
     * @param loginForm
     * 登录的表单参数
     * @return
     * 登录的结果
     */
    LoginUser login(LoginForm loginForm) throws Exception;
}
