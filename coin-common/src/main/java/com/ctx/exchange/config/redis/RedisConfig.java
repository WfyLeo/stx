package com.ctx.exchange.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    /**
     *使用RedisTemplate<String, Object> 是需要注意其序列化的方式
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);//设置工厂
        redisTemplate.setKeySerializer(new StringRedisSerializer());//String key的序列化
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());//String value的序列化
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());//hash key的序列化
        redisTemplate.setValueSerializer(new StringRedisSerializer());//hash value的序列化
        return redisTemplate;
    }

}
