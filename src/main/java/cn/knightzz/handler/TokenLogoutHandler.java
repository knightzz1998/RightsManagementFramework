package cn.knightzz.handler;


import cn.knightzz.response.R;
import cn.knightzz.security.TokenManager;
import cn.knightzz.utils.ResponseUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登出处理器
 * @author 王天赐
 */
public class TokenLogoutHandler implements LogoutHandler {

    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;

    public TokenLogoutHandler(TokenManager tokenManager, RedisTemplate redisTemplate) {
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    /**
     * Causes a logout to be completed. The method must complete successfully.
     *
     * @param request        the HTTP request
     * @param response       the HTTP response
     * @param authentication the current principal details
     */
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 1. 从header获取token
        // 2. token 不为空, 从redis移除token
        String token = request.getHeader("token");
        if (token != null) {
            tokenManager.removeToken(token);
            // 从token获取用户名
            String username = tokenManager.getUserInfoFromToken(token);
            // 移除token
            redisTemplate.delete(username);
            ResponseUtil.out(response, R.ok());
        }
        ResponseUtil.out(response, R.error());
    }
}
