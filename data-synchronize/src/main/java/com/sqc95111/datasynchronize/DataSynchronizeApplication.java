package com.sqc95111.datasynchronize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableEurekaClient
@EnableScheduling
@SpringBootApplication
@EnableFeignClients

public class DataSynchronizeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataSynchronizeApplication.class, args);
    }

}

