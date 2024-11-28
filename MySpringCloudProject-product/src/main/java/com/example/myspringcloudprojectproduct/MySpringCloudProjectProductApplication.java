package com.example.myspringcloudprojectproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MySpringCloudProjectProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringCloudProjectProductApplication.class, args);
    }

}
