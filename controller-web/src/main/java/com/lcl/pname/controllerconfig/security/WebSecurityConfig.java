package com.lcl.pname.controllerconfig.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author lcl
 * 该类为 security 总配置类
 */
@EnableWebSecurity //开启 Security
//@EnableGlobalMethodSecurity(prePostEnabled = true) 在启动类注解了,但是springboot项目可以不用加这个注解,听说是
// springboot 自动开启这个注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailServiceImpl customUserDetailServiceImpl;
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private LoginFailureHandler loginFailureHandler;
    @Autowired
    private CaptchaFilter captchaFilter;
    @Autowired
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private JwtLogoutSuccessHandler jwtLogoutSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        /*使用 BCrypt 加密密码*/
        return new BCryptPasswordEncoder();
    }

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
            return new JwtAuthenticationFilter(authenticationManager());
    }

    /**
     * 数组初始化用的是 花括号 {}
     * URL 白名单设置,访问时不需要拦截
     */
    private static final String[] URL_WHITELIST = {
            /*这个是我的登录 api 接口*/
            "/user/login",
            "/logout",
            "/user/captcha",
            "/captcha",
            "/favicon.ico"
    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                /*从数据库中读取的用户进行身份验证,设置 userDetail 和 加密方法*/
                .userDetailsService(customUserDetailServiceImpl)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /*允许跨域*/
                .cors()

                .and()
                /*关闭 csrf*/
                .csrf()
                .disable()

                //登录配置
                .formLogin()
                /*登录自定义接口路径,默认是 /login */
                .loginProcessingUrl("/user/login")
                /*登录成功处理器*/
                .successHandler(loginSuccessHandler)
                /*登录失败处理器*/
                .failureHandler(loginFailureHandler)

                //退出
                .and()
                .logout()
                .logoutSuccessHandler(jwtLogoutSuccessHandler)

                //设置不生成 session 策略
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                //配置拦截规则
                .and()
                .authorizeRequests()
                /*所有人都可以访问白名单地址
                permitAll : 会适配一个 AnonymousAuthenticationToken(匿名身份令牌) 设置到
                SecurityContextHolder,以便后面 filter 可以统一处理 authentication.
                ignore : 和 permitAll 一样也是放行,只不过他是完全绕过 所有 filter.*/
                .antMatchers(URL_WHITELIST).permitAll()
                /*这里是允许所有 Post 请求到 /xxx/xxx 地址,不需要认证*/
                //.antMatchers(HttpMethod.POST,"xxx/xxx","xxx/xxx").permitAll()
                /*需要登录,表示需要认证*/
                .anyRequest().authenticated()

                .and()
                //配置异常处理器
                .exceptionHandling()
                /*没有认证异常*/
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                /*没有权限异常*/
                .accessDeniedHandler(jwtAccessDeniedHandler)

                //配置自定义过滤器,前置过滤器,类似 addFilterxx(a,b)的方法 会在 b 之前执行 a
                .and()
                .addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        ;
    }
}
