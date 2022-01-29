package cn.knightzz.service.impl;

import cn.knightzz.entity.AclPermission;
import cn.knightzz.mapper.AclPermissionMapper;
import cn.knightzz.service.AclPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author knightzz98
 */
@Service
public class AclPermissionServiceImpl extends ServiceImpl<AclPermissionMapper, AclPermission>
        implements AclPermissionService {

    /**
     * 根据用户id查询权限信息
     *
     * @param id
     * @return 权限信息列表
     */
    @Override
    public List<String> selectPermissionValueByUserId(String id) {
        return baseMapper.selectPermissionValueByUserId(id);
    }

    /**
     * 获取所有的权限信息
     *
     * @return 权限信息列表
     */
    @Override
    public List<String> selectAllPermissionValue() {
        return baseMapper.selectAllPermissionValue();
    }

    /**
     * 根据用户id查询用户的对应角色的路由名称, 用于动态生成菜单
     *
     * @param id
     * @return 权限表中的Component值列表
     */
    @Override
    public List<String> selectComponentByUserId(String id) {

        List<String> routes = new ArrayList<>();
        // 判断id是否为空
        if (StringUtils.hasLength(id)) {
            List<AclPermission> permissionList = baseMapper.selectPermissionByUserId(id);
            permissionList.forEach(permission -> {
                if (StringUtils.hasLength(permission.getComponent())) {
                    routes.add(permission.getComponent());
                }
            });
        }
        return routes;
    }
}




