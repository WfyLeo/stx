package com.ctx.exchange.feign;

import com.ctx.exchange.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "member-service",configuration = OAuth2FeignConfig.class,path = "/users")
public interface  UserServiceFeign {

    /**
     * 用于admin-service 里面远程调用member-service
     * @param ids
     * @return
     */
    @GetMapping("/basic/users")
    Map<Long,UserDto> getBasicUsers(
            @RequestParam(value = "ids",required = false) List<Long> ids,
            @RequestParam(value = "userName",required = false) String userName ,
            @RequestParam(value = "mobile",required = false) String mobile
    ) ;
}
