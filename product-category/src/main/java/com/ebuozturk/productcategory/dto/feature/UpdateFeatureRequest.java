package com.ebuozturk.productcategory.dto.feature;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.validation.constraints.NotNull;

public record UpdateFeatureRequest(@NotNull String name, String featureTypeId, List<String> categoryIdList) {
    public UpdateFeatureRequest(@NotNull @JsonProperty("name") String name, @JsonProperty("featureTypeId") String featureTypeId, @JsonProperty("categoryIdList") List<String> categoryIdList) {
        this.name = name;
        this.featureTypeId = featureTypeId;
        this.categoryIdList = categoryIdList;
    }

    @NotNull
    @JsonProperty("name")
    public String name() {
        return this.name;
    }

    @JsonProperty("featureTypeId")
    public String featureTypeId() {
        return this.featureTypeId;
    }

    @JsonProperty("categoryIdList")
    public List<String> categoryIdList() {
        return this.categoryIdList;
    }
}
