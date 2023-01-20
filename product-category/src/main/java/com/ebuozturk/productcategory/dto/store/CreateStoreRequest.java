package com.ebuozturk.productcategory.dto.store;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record CreateStoreRequest(String name, List<String> sellerIds) {
    public CreateStoreRequest(@JsonProperty("name") String name, @JsonProperty("sellerIds") List<String> sellerIds) {
        this.name = name;
        this.sellerIds = sellerIds;
    }

    @JsonProperty("name")
    public String name() {
        return this.name;
    }

    @JsonProperty("sellerIds")
    public List<String> sellerIds() {
        return this.sellerIds;
    }
}
