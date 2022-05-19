package com.ebuozturk.basket.controller;

import com.ebuozturk.basket.dto.basket.BasketDto;
import com.ebuozturk.basket.service.BasketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/basket")
public class BasketController {
    private final BasketService service;

    public BasketController(BasketService service) {
        this.service = service;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<BasketDto> getBasketByUserId(@PathVariable("userId") String userId){
        return ResponseEntity.ok(service.getBasketByUserId(userId));
    }
    @GetMapping
    public ResponseEntity<List<BasketDto>> getAllBaskets(){
        return  ResponseEntity.ok(service.getAllBaskets());
    }

    @PostMapping("{userId}")
    public ResponseEntity<BasketDto> createBasket(@PathVariable String userId){
        return new ResponseEntity<>(service.createBasket(userId),HttpStatus.CREATED);
    }
    @PostMapping("/addProduct")
    public ResponseEntity<BasketDto> addProductToUserBasket(@RequestParam("userId") String userId,
                                                            @RequestParam("productId") String productId,
                                                            @RequestParam("amount") int amount){

        return new ResponseEntity<>(service.addProductToBasket(userId,productId,amount),HttpStatus.CREATED);
    }
//    @DeleteMapping
//    public ResponseEntity<Void> removeProductFromUserBasket(@RequestParam("userId") String userId, @RequestParam("productId") String productId){
//        service.removeProductFromBasket(userId,productId);
//        return ResponseEntity.ok().build();
//    }

}
