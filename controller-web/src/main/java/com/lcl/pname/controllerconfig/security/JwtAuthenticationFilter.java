package com.lcl.pname.controllerconfig.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lcl.pname.controllerconfig.security.utils.JwtUtils;
import com.lcl.pname.entity.User;
import com.lcl.pname.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {
        super.onSuccessfulAuthentication(request, response, authResult);
    }

    /**
     * 自定义处理对 请求头中的令牌解析(包括生成一个 Authorization) 和 用户认证
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("开启 jwt 认证");
        //super.doFilterInternal(request, ressponse, chain);

        /*获取请求头中的 token 值*/
        String jwt = request.getHeader(jwtUtils.getHeader());
        if (jwt != null) {
            /*使用 jwt 工具类 通过 解析token 获取 Claim: token中的信息*/
            Claims claim = jwtUtils.getClaimByToken(jwt);
            if (claim != null) {
                /*进来说明 token 没有失效,获取 token 中 username*/
                String username = claim.getSubject();
                logger.info("jwt 认证 : 检查用户名");
                /*SecurityHolder 是一个获取 SecurityContext 的工具*/
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    User sysUser = userService.getOne(new QueryWrapper<User>().eq("username", username));
                    Long userId = sysUser.getId();
                    /*获取 用户的 账户 和 密码 和 权限列表*/
                    UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
                    /*验证 token 是否过期*/
                    if (!jwtUtils.isTokenExpired(claim)) {
                        /*封装一个被认证的 Authorization =>*/
                        UsernamePasswordAuthenticationToken auth =
                                new UsernamePasswordAuthenticationToken(username, null, customUserDetailService.getUserAuthority(userId));
                        auth.setDetails(userDetails);
                        logger.info("通过 jwt 认证,设置 Authentication,后续过滤器放行");
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    }
                }
            }
        } else {
            logger.info("第一次登录: jwt 为空");
        }
        chain.doFilter(request, response);
    }
}
