package com.ctx.exchange.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctx.exchange.mapper.CoinRechargeMapper;
import com.ctx.exchange.domain.CoinRecharge;
import com.ctx.exchange.service.CoinRechargeService;
@Service
public class CoinRechargeServiceImpl extends ServiceImpl<CoinRechargeMapper, CoinRecharge> implements CoinRechargeService{

}
