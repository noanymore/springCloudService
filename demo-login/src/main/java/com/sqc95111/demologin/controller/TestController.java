package com.sqc95111.demologin.controller;

import com.sqc95111.demologin.constant.ResponseResult;
import com.sqc95111.demologin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ${Name}
 * @Description TODO
 * @Author < a href="jcsong50@best-inc.com">sqc</a>
 * @Date 2018/12/25 10:56
 * @Version 1.0
 */
@RequestMapping("/start")
@Api(tags = "测试部分",description = "swagger测试")
@RestController
public class TestController {
    @Autowired
    UserService userService;

    /**
     * 方法实现说明 TODO
     * 测试
     *@return ResponseResult
     *throws
     *author < a href="jcsong50@best-inc.com">sqc</a>
     *@date 2018/12/25 11:29
     */
    @ApiOperation("测试接口")
    @GetMapping("/test")
    public ResponseResult testMethod(){
        return ResponseResult.build().success();
    }





}
