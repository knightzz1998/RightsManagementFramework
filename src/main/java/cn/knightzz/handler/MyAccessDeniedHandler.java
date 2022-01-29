package cn.knightzz.handler;

import cn.knightzz.response.JsonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author knightzz98
 * @title: MyAccessDeniedHandler
 * @projectName SpringSecurityChapter
 * @description: 权限不足处理器
 * @date 2021/8/25 13:58
 */
@Slf4j
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    public MyAccessDeniedHandler() {
    }

    @Resource
    ObjectMapper objectMapper;

    /**
     * Handles an access denied failure.
     *
     * @param request               that resulted in an <code>AccessDeniedException</code>
     * @param response              so that the user agent can be advised of the failure
     * @param accessDeniedException that caused the invocation
     * @throws IOException      in the event of an IOException
     * @throws ServletException in the event of a ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("进入 MyAccessDeniedHandler ...");
        JsonResult<Object> result = new JsonResult<>(401, accessDeniedException.getMessage());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
