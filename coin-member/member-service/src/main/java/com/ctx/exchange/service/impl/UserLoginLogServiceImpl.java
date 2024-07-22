package com.ctx.exchange.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctx.exchange.domain.UserLoginLog;
import com.ctx.exchange.mapper.UserLoginLogMapper;
import com.ctx.exchange.service.UserLoginLogService;
@Service
public class UserLoginLogServiceImpl extends ServiceImpl<UserLoginLogMapper, UserLoginLog> implements UserLoginLogService{

}
