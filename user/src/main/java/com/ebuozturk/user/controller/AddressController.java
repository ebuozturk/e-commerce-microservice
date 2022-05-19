package com.ebuozturk.user.controller;

import com.ebuozturk.user.dto.address.AddressDto;
import com.ebuozturk.user.dto.address.CreateAddressRequest;
import com.ebuozturk.user.dto.address.UpdateAddressRequest;
import com.ebuozturk.user.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/address")
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AddressDto>> getAllAddressesByUserId(@PathVariable String userId){
        return ResponseEntity.ok(service.getAllAddressesByUserId(userId));
    }
    @GetMapping("{id}")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable("id") String id){
        return ResponseEntity.ok(service.getAddressById(id));
    }

    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@Valid @RequestBody CreateAddressRequest request){
        return new ResponseEntity<>(service.createAddress(request), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable String id,@RequestBody UpdateAddressRequest request){
        return ResponseEntity.ok(service.updateAddress(id,request));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable("id") String id){
        service.deleteAddress(id);
        return ResponseEntity.ok().build();
    }
}
