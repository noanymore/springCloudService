package com.sqc95111.demologin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sqc95111.demologin.constant.ResponseResult;
import com.sqc95111.demologin.mapper.UserMapper;
import com.sqc95111.demologin.model.BO.RegisterUserBO;
import com.sqc95111.demologin.model.BO.UserBO;
import com.sqc95111.demologin.model.DTO.User;
import com.sqc95111.demologin.service.MailService;
import com.sqc95111.demologin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.sqc95111.demologin.util.RandomValidateCodeUtil.RANDOMCODEKEY;

/**
 * @ClassName ${Name}
 * @Description TODO
 * @Author < a href="jcsong50@best-inc.com">sqc</a>
 * @Date 2018/12/24 10:47
 * @Version 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    MailService mailService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public User getUserByUserAccountOrName(String username) {
        return userMapper.selectOne(new QueryWrapper<User>().eq(username!=null,"username",username)
                .or(QueryWrapper-> QueryWrapper.eq(username!=null, "user_account",username)));
    }

    @Override
    public ResponseResult login(UserBO userBO) {
        return null;
    }


    @Override
    public ResponseResult userRegister(@Valid RegisterUserBO registerUserBO, HttpServletRequest request) {
        String securityCode = (String) request.getSession().getAttribute(RANDOMCODEKEY);
        if(!securityCode.equals(registerUserBO.getSecurityCode())){
            ResponseResult.build().fail().message("验证码错误");
        }
        User user = new User();
        BeanUtils.copyProperties(registerUserBO,user);
        userMapper.insert(user);
        try{
            mailService.sendMail(registerUserBO.getMail());
        }catch(Exception e){
            logger.info("UserServiceImpl.userRegister mailService.sendMail error");
        }
        return ResponseResult.build().message("注册成功,待激活").success();
    }


//    @PostConstruct
////    private void initialize() {
////        setUserMapper(userMapper);
////    }
}
