package cn.knightzz.mapper;

import cn.knightzz.entity.AclPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author knightzz98
 * @Entity cn.knightzz.entity.AclPermission
 */
public interface AclPermissionMapper extends BaseMapper<AclPermission> {

    /**
     * 根据用户id查询权限值
     *
     * @param id 用户id
     * @return 权限信息列表
     */
    List<String> selectPermissionValueByUserId(String id);

    /**
     * 获取所有的权限信息
     *
     * @return 权限信息列表
     */
    List<String> selectAllPermissionValue();

    /**
     * 获取当前用户对应角色的所有权限信息
     *
     * @param id 用户id
     * @return 权限信息列表
     */
    List<AclPermission> selectPermissionByUserId(String id);
}
