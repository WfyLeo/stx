package com.ctx.exchange.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctx.exchange.mapper.ForexClosePositionOrderMapper;
import com.ctx.exchange.domain.ForexClosePositionOrder;
import com.ctx.exchange.service.ForexClosePositionOrderService;
@Service
public class ForexClosePositionOrderServiceImpl extends ServiceImpl<ForexClosePositionOrderMapper, ForexClosePositionOrder> implements ForexClosePositionOrderService{

}
