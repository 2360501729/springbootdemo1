package com.lcl.pname.controllerconfig.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Jwt工具类,
 */
@Data
@Component //声明组件
@ConfigurationProperties(prefix = "project.jwt") //自定义启动配置文件的前缀获取属性值.
public class JwtUtils {
    /**
     * 保留天数
     */
    private long expire;
    /**
     * 密钥
     */
    private String secret;
    /**
     * {header:jwt}传给前端
     */
    private String header;

    /**
     * 生成 jwt
     *
     * @param username
     * @return
     */
    public String generateToken(String username) {
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() * expire);
        return Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setSubject(username)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact()
                ;
    }

    /**
     * 解析 jwt
     */
    public Claims getClaimByToken(String jwt) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJwt(jwt)
                    .getBody();
        } catch (Exception ignored) {
            return null;
        }
    }

    /**
     * jwt 是否过期
     */
    public boolean isTokenExpired(Claims claims) {
        //如果过期时间在当前时间之前,就代表过期
        return claims.getExpiration().before(new Date());
    }
}
