package com.ctx.exchange.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctx.exchange.mapper.CoinWithdrawAuditRecordMapper;
import com.ctx.exchange.domain.CoinWithdrawAuditRecord;
import com.ctx.exchange.service.CoinWithdrawAuditRecordService;
@Service
public class CoinWithdrawAuditRecordServiceImpl extends ServiceImpl<CoinWithdrawAuditRecordMapper, CoinWithdrawAuditRecord> implements CoinWithdrawAuditRecordService{

}
