package cn.knightzz.security;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 王天赐
 * @title: TokenManager
 * @projectName RightsManagementFramework
 * @description:
 * @website http://knightzz.cn/
 * @github https://github.com/knightzz1998
 * @date 2022/1/27 17:04
 */
@Component
public class TokenManager {

    /**
     * token有效时长
     */
    private final long TOKEN_ECPRATION = 24 * 60 * 60 * 1000;

    /**
     * 编码密钥
     */
    private String TOKEN_SIGN_KEY = "knight";


    /**
     * 根据用户名生成token
     *
     * @param username 用户名
     * @return 生成的token
     */
    public String createToken(String username) {
        String token = Jwts.builder()
                // 设置过期时间, 当前时间 + 时长
                .setSubject(username).setExpiration(new Date(System.currentTimeMillis() + TOKEN_ECPRATION))
                // 设置签名
                .signWith(SignatureAlgorithm.HS512, TOKEN_SIGN_KEY).compressWith(CompressionCodecs.GZIP).compact();
        return token;
    }

    /**
     * 根据token字符串得到用户信息
     *
     * @param token
     * @return
     */
    public String getUserInfoFromToken(String token) {
        String userInfo = Jwts.parser().setSigningKey(TOKEN_SIGN_KEY).parseClaimsJws(token).getBody().getSubject();
        return userInfo;
    }

    /**
     * 删除token
     *
     * @param token
     */
    public void removeToken(String token) {
    }
}

