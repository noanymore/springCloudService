package com.sqc95111.demologin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sqc95111.demologin.model.DTO.User;
import com.sqc95111.demologin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class TrancationTest {

    Logger logger = LoggerFactory.getLogger(TrancationTest.class);


    @Autowired
    UserService userService;


    @Transactional(rollbackFor = Exception.class)

    public  void TrancationLog() {
        User user = userService.selectOne(new QueryWrapper<User>().eq("username", "sqc"));
        logger.info("Transaction 开始执行");
        logger.info("the detail information of user", user);
        try{
        user.setUsername("sqc1");
            userService.updateById(user);
        logger.info("步骤1 the detail information of user", user.toString());
        user.setMail("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            userService.updateById(user);
        logger.info("步骤2 the detail information of user", user.toString());
        }catch(Exception e){
            logger.info("异常");
//            throw new RuntimeException();//和下面二选一
             TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

        }
    }




}
