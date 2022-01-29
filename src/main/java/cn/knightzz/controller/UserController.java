package cn.knightzz.controller;

import cn.knightzz.entity.AclUser;
import cn.knightzz.response.R;
import cn.knightzz.security.TokenManager;
import cn.knightzz.service.AclPermissionService;
import cn.knightzz.service.AclRoleService;
import cn.knightzz.service.AclUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author knightzz98
 * @title: UserController
 * @projectName
 * @description:
 * @date 2021/8/25 10:40
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    AclUserService userService;

    @Resource
    TokenManager tokenManager;

    @Resource
    AclPermissionService permissionService;

    @Resource
    AclRoleService roleService;

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('admin','user.list')")
    public R getAllUsers() {
        log.info("进入 UserController 的 getAllUsers 方法内!");
        List<AclUser> allUsers = userService.getAllUsers();
        return R.ok().data("users", allUsers);
    }

    @GetMapping("/info")
    public R getInfo(@RequestParam("token") String token) {

        /**
         * TODO : avatar , buttons: [](permission_value) roles: ['admin'...] , routes: ['User', 'Dashboard']
         * TODO tips: Dashboard , 是路由的名字, 所有的路由都在index.js 定义过了, 需要过滤
         */

        // 解析token, 获取用户名
        String username = tokenManager.getUserInfoFromToken(token);
        if (StringUtils.hasLength(username)) {
            // 根据用户名获取用户信息
            AclUser aclUser = userService.selectUserByUsername(username);
            // 获取button权限信息
            List<String> buttons = permissionService.selectPermissionValueByUserId(aclUser.getId());
            // 获取路由名称列表
            List<String> routes = permissionService.selectComponentByUserId(aclUser.getId());
            // 获取角色名称列表
            List<String> roles = roleService.selectRoleNameByUserId(aclUser.getId());

            Map<String, Object> data = new HashMap<>();
            data.put("name", aclUser.getUsername());
            data.put("avatar", aclUser.getAvatar());
            data.put("buttons", buttons);
            data.put("routes", routes);
            data.put("roles", roles);
            return R.ok().data(data);
        }
        return R.error().message("用户信息获取失败");
    }
}
