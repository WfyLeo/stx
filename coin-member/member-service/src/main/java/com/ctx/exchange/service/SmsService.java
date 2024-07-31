package com.ctx.exchange.service;

import com.ctx.exchange.domain.Sms;

public interface SmsService {

    /**
     * 发送一个短信
     * @param sms
     * 短信内容
     * @return
     * 是否发送成功
     */
    boolean sendSms(Sms sms);
}
