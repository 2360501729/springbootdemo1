package com.lcl.pname.controllerconfig.Interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcl.pname.appcontext.ProjectAutoConfiguration;
import com.lcl.pname.responsestatus.R;
import com.lcl.pname.responsestatus.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author lcl
 * 权限验证拦截器
 */
public class AuthorizedInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("AuthorizedInterceptor.preHandle");
//        return HandlerInterceptor.super.preHandle(request, response, handler);
        /*HttpSession session = request.getSession();
        Object user = session.getAttribute(ProjectAutoConfiguration.sessionUserKey);
        if (user == null) {
            //说明没有用户存在,将进行拦截
            ObjectMapper objectMapper = new ObjectMapper();
            R<String> loginStatus = R.error(ResultCode.LOGIN_AUTH,"请登录");
            String jsonStatus = objectMapper.writeValueAsString(loginStatus);
            //设置响应编码
            response.setContentType("application/json;charset=utf-8");
            //设置网络响应
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(jsonStatus);
            return false;
        }*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("AuthorizedInterceptor.postHandle");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("AuthorizedInterceptor.afterCompletion");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
