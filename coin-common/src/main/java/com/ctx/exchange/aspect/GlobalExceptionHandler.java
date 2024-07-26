package com.ctx.exchange.aspect;

import com.baomidou.mybatisplus.extension.api.IErrorCode;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.ctx.exchange.model.R;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局web的异常处理
 * @ControllerAdvice 和 @ResponseBody 的功能，用于全局处理控制器中的异常、数据绑定和其他 Web 相关的操作。
 * 些方法可以捕获应用程序中的所有异常，并且返回统一的响应结构
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public R handlerApiException(ApiException exception) {
        IErrorCode errorCode = exception.getErrorCode();
        if (errorCode != null) {
            return R.fail(errorCode);
        }
        return R.fail(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                return R.fail(fieldError.getField() + fieldError.getDefaultMessage());
            }
        }
        return R.fail(exception.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public R handlerBindException(BindException bindException) {
        BindingResult bindingResult = bindException.getBindingResult();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                return R.fail(fieldError.getField() + fieldError.getDefaultMessage());
            }
        }
        return R.fail(bindException.getMessage());
    }

    /**
     * 4 捕获 Feign 异常并自定义返回消息
     */
/*    @ExceptionHandler(FeignException.class)
    public R handleFeignException(FeignException exception) {
        // 判断是否是BadRequest（400）错误
        if (exception.status() == HttpStatus.BAD_REQUEST.value()) {
            return R.fail("用户名或密码错误");
        }
        // 对其他类型的FeignException进行处理
        return R.fail("服务调用异常: " + exception.getMessage());
    }*/
}
