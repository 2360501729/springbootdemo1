package com.lcl.pname.controllerconfig.security;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lcl.pname.appcontext.ProjectAutoConfiguration;
import com.lcl.pname.responsestatus.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.io.IOException;

/**
 * 该类用于验证码验证
 * 因为验证码只需要验证一次,所以继承 OncePerRequestFilter
 * 自己写的过滤器要在security配置类中配置
 */

@Slf4j
@Component //声明为组件
public class CaptchaFilter  extends OncePerRequestFilter {

    @Autowired
    LoginFailureHandler loginFailureHandler;

//    @Autowired
//    RedisUtil redisUtil;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("开始验证验证码");
        String requestURI = request.getRequestURI();
        /*只有登录uri才会验证验证码*/
        if ("/login".equals(requestURI) && "POST".equals(request.getMethod())) {
            try {
                /*校验验证码*/
                validate(request);
            } catch (CaptchaException exception) {
                /*这里应该抛出验证码异常,但是还没有自定义验证码异常*/
                loginFailureHandler.onAuthenticationFailure(request, response, exception);
            }
        }
        //将请求转发给下一个过滤器
        filterChain.doFilter(request,response);
    }

    /**
     * 校验验证码逻辑
     *
     * @param request
     */
    private void validate(HttpServletRequest request) {
        String key = request.getParameter("tokens");
        String code = request.getParameter("code");
        if (StringUtils.isBlank(key) || StringUtils.isBlank(code)) {
            throw new CaptchaException("验证码信息错误");
        }
        if (!code.equals(redisTemplate.opsForHash().get(ProjectAutoConfiguration.captchaKey, key))) {
            throw new CaptchaException("验证码错误");
        }
        //保证每个验证码只使用一次
        redisTemplate.opsForHash().delete(ProjectAutoConfiguration.captchaKey, key);
    }
}
