package com.lcl.pname.controllerconfig.security;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lcl.pname.appcontext.ProjectAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private StringRedisTemplate stringRedisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("开始验证验证码");
        String requestURI = request.getRequestURI();
        /*只有登录 uri 才会验证验证码*/
        /*这个登录接口有三个地方需要统一: 这里 和 websecurity 中的登录页设置 和 放行白名单*/
        if ("/user/login".equals(requestURI) && "POST".equals(request.getMethod())) {
            try {
                /*校验验证码*/
                validate(request);
            } catch (CaptchaException exception) {
                /*这里应该抛出验证码异常,自定义了一个验证码异常*/
                loginFailureHandler.onAuthenticationFailure(request, response, exception);
            }
        }
        //将请求转发给下一个过滤器
        filterChain.doFilter(request,response);
    }

    /**
     * 校验验证码逻辑,
     * 1.判断 input 验证码和用户名是否有空,
     * 2.先获取生成验证码时在请求头中放的验证码的键,其次根据这个键从redis中获取验证码,再根据 input 中的验证码相对比
     *
     * @param request
     */
    private void validate(HttpServletRequest request) {
        /*方法一 : 这种获取验证码的key方式写死了,实际应用获取 key 的 方法2 */
        //String key = request.getParameter(ProjectAutoConfiguration.TOKENS_KEY);
        /*方法二: 这里获取 在请求获取验证码时放在请求头中生成的随机验证码 根据 tokensKey*/
        String key = request.getHeader(ProjectAutoConfiguration.CHECK_CODE_KEY);
        String code = request.getParameter(ProjectAutoConfiguration.TOKENS_KEY);
        log.info("上传验证码 key:{} value:{}", key, code);
        if (StringUtils.isBlank(key) || StringUtils.isBlank(code)) {
            throw new CaptchaException("验证信息错误");
        }
        /* 使用redis : 使用配置的的 hash 表名,通过 上传的 TOKENS_KEY 作为键,从 hash 表中获取验证码的值 */
        Object anObject = stringRedisTemplate.opsForHash().get(ProjectAutoConfiguration.captchaKey, key);
        if (!code.equals(anObject)) {
            throw new CaptchaException("验证码错误");
        }
        //保证每个验证码只使用一次
        stringRedisTemplate.opsForHash().delete(ProjectAutoConfiguration.captchaKey, key);
    }
}
