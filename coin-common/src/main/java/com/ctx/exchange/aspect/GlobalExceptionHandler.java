package com.ctx.exchange.aspect;

import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.ctx.exchange.model.R;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局web的异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 内部Api 通用的异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = ApiException.class)
    public R handler(ApiException e ){
        if (e.getErrorCode() != null) {
            return R.fail(e.getErrorCode());
        }
        return R.fail(e.getMessage());
    }

    /**
     * 方法参数校验失败的异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handlerValidException(MethodArgumentNotValidException e){
        BindingResult result = e.getBindingResult();
        if(result.hasErrors()){
            FieldError error = result.getFieldError();
            if(error != null){
                return R.fail(error.getField() + " " + error.getDefaultMessage());
            }
        }
        return R.fail(e.getMessage());
    }

    /**
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public R handleValidException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField() + fieldError.getDefaultMessage();
            }
        }
        return R.fail(message);
    }
}
