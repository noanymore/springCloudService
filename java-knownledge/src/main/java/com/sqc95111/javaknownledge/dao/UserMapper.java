package com.sqc95111.javaknownledge.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sqc95111.javaknownledge.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName ${Name}
 * @Description TODO
 * @Author < a href="jcsong50@best-inc.com">sqc</a>
 * @Date 2018/12/24 10:45
 * @Version 1.0
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    User selectUserByName(@RequestParam("username") String username);
}
