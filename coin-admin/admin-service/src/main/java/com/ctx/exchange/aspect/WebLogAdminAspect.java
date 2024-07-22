package com.ctx.exchange.aspect;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.alibaba.fastjson.JSON;
import com.ctx.exchange.domain.SysUserLog;
import com.ctx.exchange.model.WebLog;
import com.ctx.exchange.service.SysUserLogService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Order(2)
@Slf4j
public class WebLogAdminAspect {

    @Autowired
    private SysUserLogService sysUserLogService;

    /**
     * 雪花算法  1机器的ID   2应用的ID
     */
    private Snowflake snowflake = new Snowflake(1, 1);

    /**
     * 定义切点
     */
    //com.ctx.exchange.controller下面的所有类，所有方法，方法的所有参数
    @Pointcut("execution(* com.ctx.exchange.controller.*.*(..))")
    public void webLog(){

    }

    /**
     * 换饶通知
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = "webLog()")
    public Object WebLogAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        WebLog weblog = new WebLog();
        //创建计时器
        long start = System.currentTimeMillis() ;
        // 执行方法的真实调用
        result = joinPoint.proceed(joinPoint.getArgs());
        long end = System.currentTimeMillis() ;

        weblog.setSpendTime((int)(end - start)/1000);

        //获取请求上下文
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //获取安全请求上下文（登录的用户）
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //获取方法
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        //获取描述，我们一般都是用@ApiOperation的value写方法描述的，所以获取他
        ApiOperation annotation = method.getAnnotation(ApiOperation.class);
        //获取目标对象的类型名称
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的url地址
        String url = request.getRequestURL().toString();
        weblog.setUrl(url);
        weblog.setUri(request.getRequestURI()); // 设置请求的uri
        weblog.setMethod(method.getName());
        weblog.setBasePath(StrUtil.removeSuffix(url, URLUtil.url(url).getPath()));// http://ip:port/
        weblog.setUsername(authentication == null || authentication.getPrincipal().equals("anonymousUser") ? "" : authentication.getPrincipal().toString()); //用户的ID
        weblog.setIp(request.getRemoteAddr()); //todo 获取ip地址
        weblog.setDescription(annotation == null ? "no desc" : annotation.value());
        weblog.setMethod(className+"."+method.getName());
        weblog.setParameter(getMethodParameter(method,joinPoint.getArgs()));
        weblog.setResult(result);
        log.info(JSON.toJSONString(weblog,true));

        SysUserLog sysUserLog = new SysUserLog();
        sysUserLog.setId(snowflake.nextId());
        sysUserLog.setCreated(new Date());
        sysUserLog.setDescription(weblog.getDescription());
        sysUserLog.setGroup(weblog.getDescription());
        if(StringUtils.isNotBlank(weblog.getUsername())){
            sysUserLog.setUserId(Long.parseLong(weblog.getUsername()));
        }
        sysUserLog.setMethod(weblog.getMethod());
        sysUserLog.setIp(weblog.getIp());
        sysUserLog.setRemark(weblog.getDescription());
        sysUserLogService.save(sysUserLog);

        return result;
    }

    /**
     *
     * @param method
     * @param args
     * @return
     */
    private Object getMethodParameter(Method method, Object[] args) {
        Map<String, Object> map = new HashMap<>();
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = discoverer.getParameterNames(method);
        for (int i = 0; i <parameterNames.length ; i++) {
            if(parameterNames[i].equals("password") || parameterNames[i].equals("file")){
                map.put(parameterNames[i],"受限的支持类型") ;
            }else{
                map.put(parameterNames[i],args[i]) ;
            }
        }
        return map;
    }
}
