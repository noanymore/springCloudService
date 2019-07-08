package com.sqc95111.demologin.controller;

import com.sqc95111.demologin.constant.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ${Name}
 * @Description TODO
 * @Author < a href="jcsong50@best-inc.com">sqc</a>
 * @Date 2019/1/23 16:48
 * @Version 1.0
 */
@RestController
//@EnableGlobalMethodSecurity(prePostEnabled=true)
public class AuthenTestController {

    @PostMapping("/amdin")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseResult adminTest(){
        return ResponseResult.build().message("RolesAllows took effect");
    }
}
