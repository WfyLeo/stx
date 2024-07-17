package com.ctx.exchange.service;

import com.ctx.exchange.model.LoginResult;

public interface SysLoginService {
    LoginResult login(String username, String password);
}
