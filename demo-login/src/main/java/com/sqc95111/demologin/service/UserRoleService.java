package com.sqc95111.demologin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sqc95111.demologin.model.DTO.UserRole;

/**
 * @ClassName ${Name}
 * @Description TODO
 * @Author < a href="jcsong50@best-inc.com">sqc</a>
 * @Date 2018/12/26 0:34
 * @Version 1.0
 */
public interface UserRoleService extends IService<UserRole> {

    UserRole getUserRole(Integer userId);
}
