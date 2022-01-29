package cn.knightzz.service;

import cn.knightzz.entity.AclRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 * @author knightzz98
 */
public interface AclRoleService extends IService<AclRole> {


    /**
     * 根据用户id获取用户角色名称
     * @param userId
     * @return
     */
    public List<String> selectRoleNameByUserId(String userId);
}
