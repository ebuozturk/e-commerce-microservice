package com.ebuozturk.productcategory.dto.brand;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateBrandRequest(String name) {
    public CreateBrandRequest(@JsonProperty("name") String name) {
        this.name = name;
    }

    @JsonProperty("name")
    public String name() {
        return this.name;
    }
}