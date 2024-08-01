package com.ctx.exchange.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctx.exchange.domain.ForexOpenPositionOrder;
import com.ctx.exchange.mapper.ForexOpenPositionOrderMapper;
import com.ctx.exchange.service.ForexOpenPositionOrderService;
@Service
public class ForexOpenPositionOrderServiceImpl extends ServiceImpl<ForexOpenPositionOrderMapper, ForexOpenPositionOrder> implements ForexOpenPositionOrderService{

}
