package com.sqc95111.demologin.controller;

import com.sqc95111.demologin.constant.ResponseResult;
import com.sqc95111.demologin.model.BO.RegisterUserBO;
import com.sqc95111.demologin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName ${Name}
 * @Description TODO
 * @Author < a href="jcsong50@best-inc.com">sqc</a>
 * @Date 2018/12/25 14:21
 * @Version 1.0
 */

@Controller
@Api(tags = "登录部分",description = "描述用户登录行为")
public class LoginController {

    @Autowired
    UserService userService;
    @Value("${server.port}")
    private String serverPort;


    @ApiOperation("用户登录")
    @GetMapping("/login")
    public String userLoign(){
        return "demo_sign";
    }

//    public ResponseResult userLogin(@RequestBody UserBO userBO){
//        return userService.login(userBO);
//    }

//    @ResponseBody
//    @GetMapping("/hello")
//    public String hello(){
//        return "/hello";
//    }

    @GetMapping("/login-error")
    public String loginError(){
        return "login-error";
    }


    @ResponseBody
    @ApiOperation("用户注册")
    @PostMapping("register")
    public ResponseResult userRegister(@RequestBody RegisterUserBO registerUserBO, HttpServletRequest request){
        return  userService.userRegister(registerUserBO,request);
    }


//    @PostMapping("/whoim")
//    @ResponseBody
//    public Object whoIm() {
//        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    }


}
