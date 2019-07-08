package com.sqc95111.javaknownledge;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages ={"com.sqc95111.javaknownledge.dao"})
public class JavaKnownledgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaKnownledgeApplication.class, args);
    }

}
