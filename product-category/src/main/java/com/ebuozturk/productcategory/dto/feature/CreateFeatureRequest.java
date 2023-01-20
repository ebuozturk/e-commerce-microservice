package com.ebuozturk.productcategory.dto.feature;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.validation.constraints.NotNull;

public record CreateFeatureRequest(@NotNull String name, @NotNull String featureTypeId, @NotNull List<String> categoryIdList) {
    public CreateFeatureRequest(@NotNull @JsonProperty("name") String name, @NotNull @JsonProperty("featureTypeId") String featureTypeId, @NotNull @JsonProperty("categoryIdList") List<String> categoryIdList) {
        this.name = name;
        this.featureTypeId = featureTypeId;
        this.categoryIdList = categoryIdList;
    }

    @NotNull
    @JsonProperty("name")
    public String name() {
        return this.name;
    }

    @NotNull
    @JsonProperty("featureTypeId")
    public String featureTypeId() {
        return this.featureTypeId;
    }

    @NotNull
    @JsonProperty("categoryIdList")
    public List<String> categoryIdList() {
        return this.categoryIdList;
    }
}
