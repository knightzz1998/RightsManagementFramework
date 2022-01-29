package cn.knightzz.service.impl;

import cn.knightzz.entity.AclUser;
import cn.knightzz.entity.SecurityUser;
import cn.knightzz.service.AclPermissionService;
import cn.knightzz.service.AclUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author knightzz98
 * @title: UserDetailServiceImpl
 * @projectName SpringSecurityChapter
 * @description:
 * @date 2021/8/23 20:34
 */
@Slf4j
@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    AclUserService userService;

    @Resource
    AclPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("进入 UserDetailServiceImpl 类的 loadUserByUsername 方法内!");

        // 在数据库中根据用户名查询用户信息
        AclUser aclUser = userService.selectUserByUsername(username);
        if (aclUser == null) {
            throw new UsernameNotFoundException("用户名为 : " + username + " 的用户不存在");
        }

        // 获取权限列表
        List<String> permissionValueList = permissionService.selectPermissionValueByUserId(aclUser.getId());

        // TODO : User(String username, String password, Collection<? extends GrantedAuthority> authorities)
        SecurityUser user = new SecurityUser(aclUser);
        user.setPermissionValueList(permissionValueList);
        log.info("离开 UserDetailServiceImpl 类的 loadUserByUsername 方法内!");
        return user;
    }
}
