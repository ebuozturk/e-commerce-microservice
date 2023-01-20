package com.ebuozturk.productcategory.dto.featureType;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FeatureTypeDto(String id, String name) {
    public FeatureTypeDto(@JsonProperty("id") String id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    @JsonProperty("id")
    public String id() {
        return this.id;
    }

    @JsonProperty("name")
    public String name() {
        return this.name;
    }
}
