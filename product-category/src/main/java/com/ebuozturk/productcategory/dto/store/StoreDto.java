
package com.ebuozturk.productcategory.dto.store;

import com.ebuozturk.productcategory.dto.seller.SellerDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record StoreDto(String id, String name, List<SellerDto> sellers) {
    public StoreDto(@JsonProperty("id") String id, @JsonProperty("name") String name, @JsonProperty("sellers") List<SellerDto> sellers) {
        this.id = id;
        this.name = name;
        this.sellers = sellers;
    }

    @JsonProperty("id")
    public String id() {
        return this.id;
    }

    @JsonProperty("name")
    public String name() {
        return this.name;
    }

    @JsonProperty("sellers")
    public List<SellerDto> sellers() {
        return this.sellers;
    }
}
