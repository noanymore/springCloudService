package com.sqc95111.demologin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sqc95111.demologin.mapper.UserRoleMapper;
import com.sqc95111.demologin.model.DTO.UserRole;
import com.sqc95111.demologin.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName ${Name}
 * @Description TODO
 * @Author < a href="jcsong50@best-inc.com">sqc</a>
 * @Date 2018/12/26 0:34
 * @Version 1.0
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Autowired
    UserRoleMapper userRoleMapper;
    @Override
    public UserRole getUserRole(Integer userId) {
        return userRoleMapper.selectOne(new QueryWrapper<UserRole>().eq("user_id",userId));
    }
}
