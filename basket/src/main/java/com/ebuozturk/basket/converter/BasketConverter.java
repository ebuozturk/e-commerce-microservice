package com.ebuozturk.basket.converter;

import com.ebuozturk.basket.dto.basket.BasketDto;
import com.ebuozturk.basket.dto.product.Product;
import com.ebuozturk.basket.model.Basket;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BasketConverter {

    private final BasketProductConverter productConverter;
    private final WebClient.Builder webClientBuilder;

    public BasketConverter(BasketProductConverter productConverter, WebClient.Builder webClientBuilder) {
        this.productConverter = productConverter;
        this.webClientBuilder = webClientBuilder;
    }

    public BasketDto convert(Basket basket){

        return new BasketDto(basket.getId(), basket.getUserId(),productConverter.convert(basket.getProducts()),calculateTotalPrice(basket));
    }
    public List<BasketDto> convert(List<Basket> baskets){
        return baskets.stream().map(this::convert).collect(Collectors.toList());
    }


    private Double calculateTotalPrice(Basket basket){

        return basket.getProducts().stream().mapToDouble(i -> {
            return webClientBuilder.build()
                    .get()
                    .uri("http://PRODUCT-CATEGORY/v1/product/" + i.getProductId())
                    .retrieve()
                    .bodyToMono(Product.class)
                    .block().unitPrice() * i.getQuantity();
        }).sum();
    }
}

