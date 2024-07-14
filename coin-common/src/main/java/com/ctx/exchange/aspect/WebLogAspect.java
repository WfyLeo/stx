package com.ctx.exchange.aspect;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.ctx.exchange.model.WebLog;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Order(1)
public class WebLogAspect {

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
        WebLog log = new WebLog();
        //创建计时器
        long start = System.currentTimeMillis() ;
        // 执行方法的真实调用
        result = joinPoint.proceed(joinPoint.getArgs());
        long end = System.currentTimeMillis() ;

        log.setSpendTime((int)(end - start)/1000);

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
        log.setUrl(url);
        log.setUri(request.getRequestURI()); // 设置请求的uri
        log.setMethod(method.getName());
        log.setBasePath(StrUtil.removePrefix(url, URLUtil.url(url).getPath()));// http://ip:port/
        log.setUsername(authentication == null ? "" : authentication.getPrincipal().toString()); //用户的ID
        log.setIp(request.getRemoteAddr()); //todo 获取ip地址
        log.setDescription(annotation == null ? "no desc" : annotation.value());
        log.setMethod(className+"."+method.getName());
        log.setParameter(getMethodParameter(method,joinPoint.getArgs()));
        log.setResult(result);
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
