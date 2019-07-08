package com.sqc95111.demologin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sqc95111.demologin.mapper.RoleMapper;
import com.sqc95111.demologin.model.DTO.Role;
import com.sqc95111.demologin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName ${Name}
 * @Description TODO
 * @Author < a href="jcsong50@best-inc.com">sqc</a>
 * @Date 2018/12/26 0:35
 * @Version 1.0
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Override
    public Role getRole(Integer roleId) {
        return roleMapper.selectOne(new QueryWrapper<Role>().eq("role_id",roleId));
    }
}
