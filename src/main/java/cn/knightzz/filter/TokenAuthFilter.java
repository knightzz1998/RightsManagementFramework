package cn.knightzz.filter;

import cn.knightzz.security.TokenManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 王天赐
 * @title: TokenAuthFilter
 * @projectName RightsManagementFramework
 * @description:
 * @website http://knightzz.cn/
 * @github https://github.com/knightzz1998
 * @date 2022/1/27 17:02
 */
public class TokenAuthFilter extends BasicAuthenticationFilter {

    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;

    public TokenAuthFilter(AuthenticationManager authenticationManager,
                           TokenManager tokenManager, RedisTemplate redisTemplate) {
        super(authenticationManager);
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 获取当前认证成功的用户权限
        UsernamePasswordAuthenticationToken authResult = getAuthentication(request);
        // 如果有权限信息, 就放到当前权限的上下文中
        if (authResult != null) {
            SecurityContextHolder.getContext().setAuthentication(authResult);
        }
        // 放行
        chain.doFilter(request, response);
    }

    /**
     * 从header中获取token, 并从token中获取用户名=> 从Redis中根据用户名获取权限列表
     *
     * @param request
     * @return
     */
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {

        // 从header中拿到token
        String token = request.getHeader("token");
        if (token != null) {
            // 从token中获取用户名
            String username = tokenManager.getUserInfoFromToken(token);
            // 从redis中获取用户权限列表
            //region 封装权限列表
            List<String> permissionValueList = (List<String>) redisTemplate.opsForValue().get(username);
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            for (String permissionValue : permissionValueList) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permissionValue);
                authorities.add(authority);
            }
            //endregion
            // 参数 : 用户名, token, 权限列表
            return new UsernamePasswordAuthenticationToken(username, token, authorities);
        }
        return null;
    }
}