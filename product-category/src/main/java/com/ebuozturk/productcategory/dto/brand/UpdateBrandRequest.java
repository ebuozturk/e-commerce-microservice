package com.ebuozturk.productcategory.dto.brand;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateBrandRequest(String name) {
    public UpdateBrandRequest(@JsonProperty("name") String name) {
        this.name = name;
    }

    @JsonProperty("name")
    public String name() {
        return this.name;
    }
}
