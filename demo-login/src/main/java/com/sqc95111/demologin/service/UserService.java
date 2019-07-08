package com.sqc95111.demologin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sqc95111.demologin.constant.ResponseResult;
import com.sqc95111.demologin.model.BO.RegisterUserBO;
import com.sqc95111.demologin.model.BO.UserBO;
import com.sqc95111.demologin.model.DTO.User;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @ClassName ${Name}
 * @Description TODO
 * @Author < a href="jcsong50@best-inc.com">sqc</a>
 * @Date 2018/12/24 10:46
 * @Version 1.0
 */
public interface UserService extends IService<User> {

    User getUserByUserAccountOrName(String userAccount);

    ResponseResult login(UserBO userBO);

    ResponseResult userRegister(@Valid RegisterUserBO registerUserBO, HttpServletRequest request);
}
