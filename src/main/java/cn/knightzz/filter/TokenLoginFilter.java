package cn.knightzz.filter;

import cn.knightzz.entity.AclUser;
import cn.knightzz.entity.SecurityUser;
import cn.knightzz.response.R;
import cn.knightzz.security.TokenManager;
import cn.knightzz.utils.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 王天赐
 * @title: TokenLoginFilter
 * @projectName RightsManagementFramework
 * @description:
 * @website http://knightzz.cn/
 * @github https://github.com/knightzz1998
 * @date 2022/1/27 17:03
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;
    /**
     * 权限管理对象
     */
    private AuthenticationManager authenticationManager;

    public TokenLoginFilter(AuthenticationManager authenticationManager, TokenManager tokenManager, RedisTemplate redisTemplate) {
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
        this.authenticationManager = authenticationManager;

        // 设置仅POST提交为false
        this.setPostOnly(false);
        // 设置登陆路径和请求方式
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
    }

    /**
     * TODO 获取表单提交用户名和密码
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 获取登陆表单的用户名和密码
        try {
            // TODO : 注意这里的用户名和密码是JSON类型的 ,  ObjectMapper() 将数据流转换为JSON类型
            AclUser aclUser = new ObjectMapper().readValue(request.getInputStream(), AclUser.class);
            return authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(aclUser.getUsername(), aclUser.getPassword()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 认证成功会调用的方法
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 认证成功以后, 得到的用户信息
        SecurityUser securityUser = (SecurityUser) authResult.getPrincipal();
        // 根据用户名生成token
        String token = tokenManager.createToken(securityUser.getUsername());
        // 将用户名和权限列表放到redis
        redisTemplate.opsForValue().set(securityUser.getCurrentUser().getUsername(),
                securityUser.getPermissionValueList());
        // 返回token
        ResponseUtil.out(response, R.ok().data("token", token));
    }

    /**
     * 认证失败会调用的方法
     *
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ResponseUtil.out(response, R.error());
    }
}