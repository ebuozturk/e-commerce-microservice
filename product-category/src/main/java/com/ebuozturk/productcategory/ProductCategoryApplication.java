package com.ebuozturk.productcategory;

import com.ebuozturk.productcategory.repository.es.ProductEsRepository;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;

import java.util.stream.Collectors;

@SpringBootApplication
@EnableEurekaClient
public class ProductCategoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductCategoryApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI();
    }

}
