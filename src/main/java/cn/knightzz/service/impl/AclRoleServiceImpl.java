package cn.knightzz.service.impl;

import cn.knightzz.entity.AclRole;
import cn.knightzz.mapper.AclRoleMapper;
import cn.knightzz.service.AclRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author knightzz98
 */
@Service
public class AclRoleServiceImpl extends ServiceImpl<AclRoleMapper, AclRole>
        implements AclRoleService {
    /**
     * 根据用户id获取用户角色名称
     *
     * @param userId
     * @return
     */
    @Override
    public List<String> selectRoleNameByUserId(String userId) {
        List<String> roles = new ArrayList<>();
        if (StringUtils.hasLength(userId)) {
            List<AclRole> roleList = baseMapper.selectRoleByUserId(userId);
            roleList.forEach(role -> {
                roles.add(role.getRoleName());
            });
        }
        return roles;
    }
}




