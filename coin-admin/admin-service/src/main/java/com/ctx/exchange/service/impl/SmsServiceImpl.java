package com.ctx.exchange.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctx.exchange.mapper.SmsMapper;
import com.ctx.exchange.domain.Sms;
import com.ctx.exchange.service.SmsService;
@Service
public class SmsServiceImpl extends ServiceImpl<SmsMapper, Sms> implements SmsService{

}
