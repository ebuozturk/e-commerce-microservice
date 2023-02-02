package com.ebuozturk.order.client;

import com.ebuozturk.order.dto.basket.Basket;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="basket",path="/v1/basket",contextId = "basket-server")
public interface BasketServiceClient {

    @GetMapping({"/user/{userId}"})
    ResponseEntity<Basket> getBasketByUserId(@PathVariable("userId") String userId);
}
