package cn.knightzz.mapper;

import cn.knightzz.entity.AclRole;
import cn.knightzz.entity.AclUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author knightzz98
 * @Entity cn.knightzz.entity.AclUserRole
 */
public interface AclUserRoleMapper extends BaseMapper<AclUserRole> {

    /**
     * 根据用户id查询当前用户所有的角色
     * @param id
     * @return 用户角色列表, 如果没有角色返回空列表
     */
    public List<AclRole> getRoleListByUserId(String id);

}




