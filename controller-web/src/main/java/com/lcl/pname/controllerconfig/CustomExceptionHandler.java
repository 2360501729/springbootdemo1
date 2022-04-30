package com.lcl.pname.controllerconfig;

import com.lcl.pname.responsestatus.R;
import com.lcl.pname.responsestatus.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 异常处理类
 */
@RestControllerAdvice //ControllerAdvice 和 ResponseBody 合体
public class CustomExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public R<String> noFoundHandlerException(NoHandlerFoundException noHandlerFoundException) {
        /*没有找到处理器异常*/
        return R.error(ResultCode.NOT_FOUND,"没有找到处理器");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public R<String> noSupportRequest(HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException) {
        /*不支持的方法请求异常*/
        return R.error(ResultCode.METHOD_NOT_SUPPORTED);
    }

    /*@ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public R<String> checkIdentityFail() {
        *//*用户权限验证失败异常*//*
        return R.error(ResultCode.NOT_FOUND);
    }*/
}
