package com.ebuozturk.order.client;

import com.ebuozturk.order.dto.address.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="user",path = "/v1/address",contextId = "address-server")
public interface AddressServiceClient {

    @GetMapping("/{id}")
    ResponseEntity<Address> getAddressById(@PathVariable("id") String id);
}
