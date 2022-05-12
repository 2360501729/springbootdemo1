package com.lcl.pname.controllerconfig.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lcl.pname.controllerconfig.security.utils.JwtUtils;
import com.lcl.pname.entity.User;
import com.lcl.pname.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomUserDetailServiceImpl customUserDetailService;

    @Autowired
    private UserService userService;

//    @Autowired
//    SysUserService sysUserService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("开启 jwt 认证");
        super.doFilterInternal(request, response, chain);

        String jwt = request.getHeader(jwtUtils.getHeader());
        if (jwt != null) {
            Claims claim = jwtUtils.getClaimByToken(jwt);
            if (claim != null) {
                String username = claim.getSubject();
                logger.info("jwt 认证 : 检查用户名");
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    User sysUser = userService.getOne(new QueryWrapper<User>().eq("username",username));
                    Long userId = sysUser.getId();
                    UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
                    if (!jwtUtils.isTokenExpired(claim)) {
                        UsernamePasswordAuthenticationToken auth =
                                new UsernamePasswordAuthenticationToken(username, null,
                                        customUserDetailService.getUserAuthority(userId));
                        auth.setDetails(userDetails);
                        logger.info("通过 jwt 认证,设置 Authentication,后续过滤器放行");
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    }
                }
            } else {
                logger.info("第一次登录 jwt 为空");
            }
            chain.doFilter(request, response);
        }
    }
}
