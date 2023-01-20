package com.ebuozturk.productcategory.dto.featureType;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public record UpdateFeatureTypeRequest(@NotNull String name, String categoryId) {
    public UpdateFeatureTypeRequest(@NotNull @JsonProperty("name") String name, @JsonProperty("categoryId") String categoryId) {
        this.name = name;
        this.categoryId = categoryId;
    }

    @NotNull
    @JsonProperty("name")
    public String name() {
        return this.name;
    }

    @JsonProperty("categoryId")
    public String categoryId() {
        return this.categoryId;
    }
}
