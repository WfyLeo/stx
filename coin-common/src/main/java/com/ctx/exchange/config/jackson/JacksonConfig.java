package com.ctx.exchange.config.jackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // 注册支持 Java 8 时间类型的模块
        objectMapper.registerModule(new JavaTimeModule());
        // 创建一个简单模块
        SimpleModule simpleModule = new SimpleModule();
        // 添加一个序列化器，将 Long 类型序列化为字符串
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        // 将简单模块注册到 ObjectMapper
        objectMapper.registerModule(simpleModule);
        objectMapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 设置可见性为所有属性，包括私有属性
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        return objectMapper;
    }
}
