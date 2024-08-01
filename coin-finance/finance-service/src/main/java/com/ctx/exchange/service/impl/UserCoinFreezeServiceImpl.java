package com.ctx.exchange.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctx.exchange.domain.UserCoinFreeze;
import com.ctx.exchange.mapper.UserCoinFreezeMapper;
import com.ctx.exchange.service.UserCoinFreezeService;
@Service
public class UserCoinFreezeServiceImpl extends ServiceImpl<UserCoinFreezeMapper, UserCoinFreeze> implements UserCoinFreezeService{

}
