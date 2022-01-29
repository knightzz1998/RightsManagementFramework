package cn.knightzz.mapper;

import cn.knightzz.entity.AclUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author knightzz98
 * @Entity cn.knightzz.entity.AclUser
 */
public interface AclUserMapper extends BaseMapper<AclUser> {

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 如果查询成功, 返回用户信息的实体对象, 否则返回 NULL
     */
    public AclUser selectByUsername(String username);

    /**
     * 更新用户密码
     *
     * @param id       用户id
     * @param password 新的密码
     */
    public void updatePassword(String id, String password);
}




