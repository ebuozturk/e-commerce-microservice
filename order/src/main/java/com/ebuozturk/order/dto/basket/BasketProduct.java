package com.ebuozturk.order.dto.basket;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BasketProduct(@JsonProperty("id") String id,
                            @JsonProperty("productId") String productId,
                            @JsonProperty("quantity") Integer quantity
                            ) {
}
