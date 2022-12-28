package com.ebuozturk.images;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ImagesApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImagesApplication.class,args);
    }
}
