package com.ctx.exchange.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctx.exchange.domain.ForexAccountDetail;
import com.ctx.exchange.mapper.ForexAccountDetailMapper;
import com.ctx.exchange.service.ForexAccountDetailService;
@Service
public class ForexAccountDetailServiceImpl extends ServiceImpl<ForexAccountDetailMapper, ForexAccountDetail> implements ForexAccountDetailService{

}
