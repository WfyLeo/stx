package com.ctx.exchange.filter;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@Component
public class JwtCheckFilter implements GlobalFilter, Ordered {

    @Value("${no.require.urls:/admin/login}")
    private Set<String> noRequieCheckTokenUrl;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.获取传递来的url，校验路径是否需要token
         if(isRequireToken(exchange)){
            return chain.filter(exchange);
        }
        //2.根据传递来的url，校验是否有token，并返回
        String token = getRequireToken(exchange);
        if(StringUtils.isEmpty(token)){
            return buildeNoAuthorizationResult(exchange);
        }
        //3.根据token，在redis中查看是否有，是否过期
        Boolean hasKey = redisTemplate.hasKey(token);
        if(null != hasKey && hasKey){
            return chain.filter(exchange);
        }
        return buildeNoAuthorizationResult(exchange);
    }

    /**
     * 处理异常
     * @param exchange
     * @return
     */
    private Mono<Void> buildeNoAuthorizationResult(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().set("Content-Type","application/json");
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error","NoAuthorization");
        jsonObject.put("errorMsg","Token is Null or Error");
        DataBuffer wrap = response.bufferFactory().wrap(jsonObject.toJSONString().getBytes());
        return response.writeWith(Flux.just(wrap));
    }

    /**
     * 回去请求头的token
     * @param exchange
     * @return
     */
    private String getRequireToken(ServerWebExchange exchange) {
        String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        return token ==null ? null : token.replace("bearer ","") ;
    }

    /**
     * 判断路径是否在放行的路径中国
     * @param exchange
     * @return
     */
    private boolean isRequireToken(ServerWebExchange exchange) {
        String path = exchange.getRequest().getURI().getPath();
        return noRequieCheckTokenUrl.contains(path);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
