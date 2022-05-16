package com.lcl.pname.controllerconfig.security;

import cn.hutool.json.JSONUtil;
import com.lcl.pname.controller.UserController;
import com.lcl.pname.responsestatus.R;
import com.lcl.pname.responsestatus.ResultCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author lcls
 * 登录成功失败过滤器
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        //security 的返回值是重定向,对于前后端分离的项目需要返回 json 数据,所以使用流的形式
        //因为返回的是 void,并且返回值是 json 类型数据,所以要使用到流
        ServletOutputStream outputStream = response.getOutputStream();

        R<String> result = R.error(ResultCode.LOGIN_ERROR,exception.getMessage());

        outputStream.write(JSONUtil.toJsonStr(result).getBytes(StandardCharsets.UTF_8));

        UserController userController = new UserController();
        try {
            userController.captcha(request,response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        outputStream.flush();
//        outputStream.close();
    }
}
