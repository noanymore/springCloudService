package com.sqc95111.demologin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@ComponentScan(basePackages = {"com.sqc95111"})
@MapperScan(basePackages ={"com.sqc95111.demologin.mapper"})

public class DemoLoginApplication {


    public static void main(String[] args) {
        SpringApplication.run( DemoLoginApplication.class, args );
    }
}
