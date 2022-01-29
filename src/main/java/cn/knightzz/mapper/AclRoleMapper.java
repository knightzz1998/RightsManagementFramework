package cn.knightzz.mapper;

import cn.knightzz.entity.AclRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity cn.knightzz.entity.AclRole
 */
public interface AclRoleMapper extends BaseMapper<AclRole> {

    /**
     * 根据用户id获取对应的角色信息
     *
     * @param id
     * @return
     */
    List<AclRole> selectRoleByUserId(String id);

}




