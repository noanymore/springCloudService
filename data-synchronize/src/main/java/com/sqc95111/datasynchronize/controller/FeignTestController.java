package com.sqc95111.datasynchronize.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ${Name}
 * @Description TODO
 * @Author < a href="jcsong50@best-inc.com">sqc</a>
 * @Date 2019/3/28 19:36
 * @Version 1.0
 */
@RestController
public class FeignTestController {
    @Autowired
    FeignClientService feignClientService;


    @GetMapping("/testFeign")
    public String testFeign(){
        return feignClientService.sayHiFromClientOne();
    }

}
