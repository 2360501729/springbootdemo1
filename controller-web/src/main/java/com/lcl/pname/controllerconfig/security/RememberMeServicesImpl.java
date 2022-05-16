package com.lcl.pname.controllerconfig.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.RememberMeServices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理记住我的实现类
 */
public class RememberMeServicesImpl implements RememberMeServices {
    @Override
    public Authentication autoLogin(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("RememberMeServicesImpl.autoLogin");
        return null;
    }

    @Override
    public void loginFail(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("RememberMeServicesImpl.loginFail");
    }

    @Override
    public void loginSuccess(HttpServletRequest request, HttpServletResponse response, Authentication successfulAuthentication) {
        System.out.println("RememberMeServicesImpl.loginSuccess");
    }
}
