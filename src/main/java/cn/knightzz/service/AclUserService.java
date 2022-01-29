package cn.knightzz.service;

import cn.knightzz.entity.AclUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author knightzz98
 */
public interface AclUserService extends IService<AclUser> {
    /**
     * 获取所有的用户信息
     *
     * @return
     */
    List<AclUser> getAllUsers();

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户名对应的用户信息
     */
    AclUser selectUserByUsername(String username);
}
