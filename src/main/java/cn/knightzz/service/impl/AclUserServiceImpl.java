package cn.knightzz.service.impl;

import cn.knightzz.entity.AclUser;
import cn.knightzz.mapper.AclUserMapper;
import cn.knightzz.service.AclUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author knightzz98
 */
@Slf4j
@Service
public class AclUserServiceImpl extends ServiceImpl<AclUserMapper, AclUser>
        implements AclUserService {

    @Resource
    AclUserMapper userMapper;

    /**
     * 获取所有的用户信息
     *
     * @return
     */
    @Override
    public List<AclUser> getAllUsers() {
        try {
            return userMapper.selectList(null);
        } catch (Exception e) {
            log.error("AclUserServiceImpl 的 getAllUsers 执行异常, 异常信息 {} ", e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户名对应的用户信息
     */
    @Override
    public AclUser selectUserByUsername(String username) {
        if (StringUtils.hasLength(username)) {
            return baseMapper.selectByUsername(username);
        }
        return null;
    }
}




