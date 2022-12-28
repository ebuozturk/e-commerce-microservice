package com.ebuozturk.order.dto.basket;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public record Basket(@JsonProperty("id") String id,
                     @JsonProperty("userId") String userId,
                     @JsonProperty("products") Set<BasketProduct> products,
                     @JsonProperty("totalPrice") Double totalPrice
                     ) {
}
