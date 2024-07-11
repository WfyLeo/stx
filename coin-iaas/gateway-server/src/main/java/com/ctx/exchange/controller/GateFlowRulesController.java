package com.ctx.exchange.controller;

import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class GateFlowRulesController {
    /**
    * 获取当前系统的限流策略
    */
    @RequestMapping("/gw/flow/riles")
    public Set<GatewayFlowRule> getCurrentGatewayFlowRule(){
        return GatewayRuleManager.getRules();
    }



    /**
     * 获取自定义的api分组
     */
    @RequestMapping("/gw/api/group")
    public Set<ApiDefinition> getApiGroups(){
        return GatewayApiDefinitionManager.getApiDefinitions();
    }
}
