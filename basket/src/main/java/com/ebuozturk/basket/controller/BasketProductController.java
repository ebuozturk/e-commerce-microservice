package com.ebuozturk.basket.controller;

import com.ebuozturk.basket.dto.basketproduct.BasketProductDto;
import com.ebuozturk.basket.service.BasketProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/basketProduct")
public class BasketProductController {
    private final BasketProductService basketProductService;

    public BasketProductController(BasketProductService basketProductService) {
        this.basketProductService = basketProductService;
    }

    @PutMapping("/increase")
    public ResponseEntity<BasketProductDto> increaseBasketProduct(@RequestParam("id") String id,@RequestParam("amount") Integer amount){
        return ResponseEntity.ok(basketProductService.increaseQuantityByProductId(amount,id));
    }
    @PutMapping("/decrease")
    public ResponseEntity<BasketProductDto> decreaseBasketProduct(@RequestParam("id") String id,@RequestParam("amount") Integer amount){
        return ResponseEntity.ok(basketProductService.decreaseQuantityByProductId(amount,id));
    }

    @DeleteMapping("/{basketProductId}")
    public ResponseEntity<Void> deleteBasketProduct(@PathVariable String basketProductId){
        basketProductService.deleteBasketProduct(basketProductId);
        return ResponseEntity.ok().build();
    }
}
