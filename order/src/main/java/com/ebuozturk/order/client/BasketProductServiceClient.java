package com.ebuozturk.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="basket",path = "/v1/basketProduct",contextId = "basket-product-server")
public interface BasketProductServiceClient {

    @DeleteMapping({"/{basketProductId}"})
    ResponseEntity<Void> deleteBasketProduct(@PathVariable("basketProductId") String basketProductId);
}
