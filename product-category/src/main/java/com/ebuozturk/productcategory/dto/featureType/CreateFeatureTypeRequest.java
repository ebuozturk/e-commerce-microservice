package com.ebuozturk.productcategory.dto.featureType;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public record CreateFeatureTypeRequest(@NotNull String name, @NotNull String categoryId) {
    public CreateFeatureTypeRequest(@NotNull @JsonProperty("name") String name, @NotNull @JsonProperty("categoryId") String categoryId) {
        this.name = name;
        this.categoryId = categoryId;
    }

    @NotNull
    @JsonProperty("name")
    public String name() {
        return this.name;
    }

    @NotNull
    @JsonProperty("categoryId")
    public String categoryId() {
        return this.categoryId;
    }
}
