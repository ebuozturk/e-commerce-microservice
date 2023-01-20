package com.ebuozturk.productcategory.dto.feature;

import com.ebuozturk.productcategory.dto.featureType.FeatureTypeDto;
import com.fasterxml.jackson.annotation.JsonProperty;

public record FeatureDto(String id, String name, FeatureTypeDto featureType) {
    public FeatureDto(@JsonProperty("id") String id, @JsonProperty("name") String name, @JsonProperty("featureType") FeatureTypeDto featureType) {
        this.id = id;
        this.name = name;
        this.featureType = featureType;
    }

    @JsonProperty("id")
    public String id() {
        return this.id;
    }

    @JsonProperty("name")
    public String name() {
        return this.name;
    }

    @JsonProperty("featureType")
    public FeatureTypeDto featureType() {
        return this.featureType;
    }
}
