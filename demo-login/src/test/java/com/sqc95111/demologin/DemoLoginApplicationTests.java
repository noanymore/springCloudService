package com.sqc95111.demologin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sqc95111.demologin.mapper.UserMapper;
import com.sqc95111.demologin.model.DTO.User;
import com.sqc95111.demologin.model.DTO.UserRole;
import com.sqc95111.demologin.service.RedisService;
import com.sqc95111.demologin.service.UserRoleService;
import com.sqc95111.demologin.service.UserService;
import com.sqc95111.demologin.service.impl.TrancationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableTransactionManagement
public class DemoLoginApplicationTests {

    @Resource
    UserMapper userMapper;
    @Resource
    UserService userService;
    @Resource
    UserRoleService userRoleService;
    @Resource
    RedisService redisService;


    @Test
    public void contextLoads() {
        UserRole userRole = userRoleService.getUserRole(2);
    }

    /**
     * ==>  Preparing: SELECT Id,user_account,username,password,create_time FROM user WHERE username = ? LIMIT 1
     * ==> Parameters: sqc(String)
     * <==    Columns: Id, user_account, username, password, create_time
     * <==        Row: 1, 15356697232, sqc, 123, null
     * <==      Total: 1
     */
    @Test
    public void insert() {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", "sqc")
        );
    }

    @Test
    public void redisInsert(){
        redisService.set("sunhuijiang", "ajiang");
    }

    @Test
    public void redisGet(){
        System.out.println(redisService.get("sunhuijiang"));
    }

    @Test
    public void testTranaction() throws Exception {
        TrancationTest trancationTest = new TrancationTest();
        trancationTest.TrancationLog();
    }


}





