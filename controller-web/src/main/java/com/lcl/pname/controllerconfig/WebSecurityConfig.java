package com.lcl.pname.controllerconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author lcl
 */
@EnableWebSecurity //开启 Security
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailServiceImpl customUserDetailServiceImpl;

    @Bean
    public PasswordEncoder passwordEncoder() {
        /*使用 BCrypt 加密密码*/
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                /*从数据库中读取的用户进行身份验证*/
                .userDetailsService(customUserDetailServiceImpl)
                .passwordEncoder(passwordEncoder());
    }
}
