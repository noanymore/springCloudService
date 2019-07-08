package com.sqc95111.demologin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sqc95111.demologin.model.DTO.Role;

/**
 * @ClassName ${Name}
 * @Description TODO
 * @Author < a href="jcsong50@best-inc.com">sqc</a>
 * @Date 2018/12/26 0:33
 * @Version 1.0
 */
public interface RoleService extends IService<Role> {

    Role getRole(Integer roleId);
}
