package com.ebuozturk.basket.converter;

import com.ebuozturk.basket.dto.basketproduct.BasketProductDto;
import com.ebuozturk.basket.model.BasketProduct;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BasketProductConverter {


    public BasketProductDto convert(BasketProduct product){
        return new BasketProductDto(product.getId(),product.getQuantity(),product.getBasket().getId(),product.getProductId());
    }

    public List<BasketProductDto> convert(Set<BasketProduct> products){
        return products.stream().map(this::convert).collect(Collectors.toList());
    }
    public List<BasketProductDto> convert(List<BasketProduct> products){
        return products.stream().map(this::convert).collect(Collectors.toList());
    }
}