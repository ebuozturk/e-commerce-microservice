package com.ebuozturk.gateway;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/fb")
public class HystrixController {

    @GetMapping("/product-category")
    public String productCategoryFallback(){ return "Product-Category Service is not available."; }

    @GetMapping("/basket")
    public String basketFallback(){ return "Basket Service is not available."; }

    @GetMapping("/order")
    public String orderFallback(){ return "Order Service is not available."; }

    @GetMapping("/user")
    public String userFallback(){ return "User Service is not available."; }

    @GetMapping("/images")
    public String imagesFallback(){ return "Images Service is not available."; }
}
