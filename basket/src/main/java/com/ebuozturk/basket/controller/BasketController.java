package com.ebuozturk.basket.controller;

import com.ebuozturk.basket.dto.basket.BasketDto;
import com.ebuozturk.basket.service.BasketService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin({"*"})
@RestController
@RequestMapping({"/v1/basket"})
public class BasketController {
    private final BasketService service;

    public BasketController(BasketService service) {
        this.service = service;
    }

    @GetMapping({"/user/{userId}"})
    public ResponseEntity<BasketDto> getBasketByUserId(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(this.service.getBasketByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<List<BasketDto>> getAllBaskets() {
        return ResponseEntity.ok(this.service.getAllBaskets());
    }

    @PostMapping({"{userId}"})
    public ResponseEntity<BasketDto> createBasket(@PathVariable String userId) {
        return new ResponseEntity(this.service.createBasket(userId), HttpStatus.CREATED);
    }

    @PostMapping({"/addProduct"})
    public ResponseEntity<BasketDto> addProductToUserBasket(@RequestParam("userId") String userId, @RequestParam("productId") String productId, @RequestParam("amount") int amount) {
        return new ResponseEntity(this.service.addProductToBasket(userId, productId, amount), HttpStatus.CREATED);
    }
}
