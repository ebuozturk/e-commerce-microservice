package com.ebuozturk.order.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateOrderRequest(
        String userId,
        String deliveryAddressId,
        String billAddressId
) {
    public CreateOrderRequest(@JsonProperty("userId") String userId, @JsonProperty("deliveryAddressId") String deliveryAddressId,@JsonProperty("billAddressId") String billAddressId) {
        this.userId = userId;
        this.deliveryAddressId = deliveryAddressId;
        this.billAddressId = billAddressId;
    }

    @JsonProperty("userId")
    public String userId() {
        return userId;
    }

    @JsonProperty("deliveryAddressId")
    public String deliveryAddressId() {
        return deliveryAddressId;
    }

    @JsonProperty("billAddressId")
    public String billAddressId() {
        return billAddressId;
    }
}
