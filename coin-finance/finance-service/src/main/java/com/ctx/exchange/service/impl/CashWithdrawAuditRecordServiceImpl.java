package com.ctx.exchange.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctx.exchange.domain.CashWithdrawAuditRecord;
import com.ctx.exchange.mapper.CashWithdrawAuditRecordMapper;
import com.ctx.exchange.service.CashWithdrawAuditRecordService;
@Service
public class CashWithdrawAuditRecordServiceImpl extends ServiceImpl<CashWithdrawAuditRecordMapper, CashWithdrawAuditRecord> implements CashWithdrawAuditRecordService{

}
