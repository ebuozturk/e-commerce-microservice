package com.ebuozturk.productcategory.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.validation.constraints.NotNull;

public record GetProductByMainProductAndFeatureIdsRequest(@NotNull String mainProductId, @NotNull List<String> featureIdList) {
    public GetProductByMainProductAndFeatureIdsRequest(@NotNull @JsonProperty("mainProductId") String mainProductId, @NotNull @JsonProperty("featureIdList") List<String> featureIdList) {
        this.mainProductId = mainProductId;
        this.featureIdList = featureIdList;
    }

    @NotNull
    @JsonProperty("mainProductId")
    public String mainProductId() {
        return this.mainProductId;
    }

    @NotNull
    @JsonProperty("featureIdList")
    public List<String> featureIdList() {
        return this.featureIdList;
    }
}
