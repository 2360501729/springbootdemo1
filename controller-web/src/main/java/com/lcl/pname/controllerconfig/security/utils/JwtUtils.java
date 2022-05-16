package com.lcl.pname.controllerconfig.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
     */
    public String generateToken(String username) {
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() * expire);
        /*jwt 建造链*/
        return Jwts.builder()
                /*生成唯一ID,可以去掉 UUID 分隔符 放进去*/
                //.setId(UUID.randomUUID().toString())
                .setHeaderParam("type", "JWT")
                /*设置为哪个用户签发*/
                .setSubject(username)
                /*设置签发日期*/
                .setIssuedAt(nowDate)
                /*设置签发过期日期*/
                .setExpiration(expireDate)
                /* 设置签名方法,参数: 签名算法 盐 ,      secret 值长度过短,会被默认为 密匙为空 */
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
     * 扩展 : ExpiredJwtException : token过期会有 ExpiredJwtException 异常
     */
    public boolean isTokenExpired(Claims claims) {
        //如果过期时间在当前时间之前,就代表过期
        return claims.getExpiration().before(new Date());
    }
}
