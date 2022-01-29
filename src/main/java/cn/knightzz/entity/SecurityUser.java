package cn.knightzz.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author knightzz98
 * @title: SecurityUser
 * @projectName SpringSecurityChapter
 * @description:
 * @date 2021/8/25 19:09
 */
@Data
@Slf4j
public class SecurityUser implements UserDetails {

    /**
     * 当前登录用户
     * TODO : transient 作用是防止 AclUser 对象被序列化
     */
    private transient AclUser currentUser;

    /**
     * 当前用户权限列表
     */
    private List<String> permissionValueList;

    public SecurityUser(AclUser currentUser) {
        if (currentUser != null) {
            this.currentUser = currentUser;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (String permissionValue : permissionValueList) {
            if (StringUtils.hasLength(permissionValue)) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permissionValue);
                authorities.add(authority);
            }
        }
        return authorities;
    }


    @Override
    public String getPassword() {
        return currentUser.getPassword();
    }


    @Override
    public String getUsername() {
        return currentUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
