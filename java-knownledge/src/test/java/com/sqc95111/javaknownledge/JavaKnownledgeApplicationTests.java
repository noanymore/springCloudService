package com.sqc95111.javaknownledge;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sqc95111.javaknownledge.model.User;
import com.sqc95111.javaknownledge.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaKnownledgeApplicationTests {
    @Autowired
    UserService userService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void select(){
        User user = userService.selectOne(new QueryWrapper<User>().eq("username", "sqc"));
        System.out.println(user.getCreateTime());
    }

}
