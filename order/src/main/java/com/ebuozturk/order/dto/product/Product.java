package com.ebuozturk.order.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Product(@JsonProperty("id") String id,
                      @JsonProperty("name") String name,
                      @JsonProperty("description") String description,
                      @JsonProperty("unitPrice") Double unitPrice,
                      @JsonProperty("quantityPerUnit") Integer quantityPerUnit,
                      @JsonProperty("unitsInStock") Integer unitsInStock
                      ) {
}
