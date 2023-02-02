package com.ebuozturk.order.client;

import com.ebuozturk.order.dto.address.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="user",path = "/v1/user",contextId = "user-server")
public interface UserServiceClient {

    @GetMapping("/isExist/{userId}")
    ResponseEntity<Boolean> doesUserExist(@PathVariable("userId") String userId);

}
