package com.sqc95111.datasynchronize.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

/**
 * @ClassName ${Name}
 * @Description TODO
 * @Author < a href="jcsong50@best-inc.com">sqc</a>
 * @Date 2019/3/28 17:35
 * @Version 1.0
 */
@RestController
public class RibbonTestController {
    @Bean
    @LoadBalanced
    RestOperations restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Autowired
    RestOperations restTemplate;


    @GetMapping("/testRibbon")
    public String testRibbon(){
        return restTemplate.getForObject("http://demo-login/hello", String.class);
    }
}
