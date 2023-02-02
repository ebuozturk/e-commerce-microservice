package com.ebuozturk.order.client;

import com.ebuozturk.order.dto.product.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-category",path = "/v1/product",contextId = "product-server")
public interface ProductServiceClient {
    @GetMapping({"{id}"})
    ResponseEntity<Product> getById(@PathVariable("id") String id);
}
