package cn.knightzz.service;

import cn.knightzz.entity.AclPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author knightzz98
 */
public interface AclPermissionService extends IService<AclPermission> {

    /**
     * 根据用户id查询权限信息
     *
     * @param id 角色id
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
     * 根据用户id查询用户的对应角色的路由名称, 用于动态生成菜单
     * @param id
     * @return 权限表中的Component值列表
     */
    List<String> selectComponentByUserId(String id);
}
