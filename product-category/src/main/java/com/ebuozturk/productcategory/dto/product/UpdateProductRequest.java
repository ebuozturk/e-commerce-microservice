
package com.ebuozturk.productcategory.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record UpdateProductRequest(String mainProductId, Integer unitsInStock, Double unitPrice, Integer quantityPerUnit, List<String> featureList) {
    public UpdateProductRequest(@JsonProperty("mainProductId") String mainProductId, @JsonProperty("unitsInStock") Integer unitsInStock, @JsonProperty("unitPrice") Double unitPrice, @JsonProperty("quantityPerUnit") Integer quantityPerUnit, @JsonProperty("featureList") List<String> featureList) {
        this.mainProductId = mainProductId;
        this.unitsInStock = unitsInStock;
        this.unitPrice = unitPrice;
        this.quantityPerUnit = quantityPerUnit;
        this.featureList = featureList;
    }

    @JsonProperty("mainProductId")
    public String mainProductId() {
        return this.mainProductId;
    }

    @JsonProperty("unitsInStock")
    public Integer unitsInStock() {
        return this.unitsInStock;
    }

    @JsonProperty("unitPrice")
    public Double unitPrice() {
        return this.unitPrice;
    }

    @JsonProperty("quantityPerUnit")
    public Integer quantityPerUnit() {
        return this.quantityPerUnit;
    }

    @JsonProperty("featureList")
    public List<String> featureList() {
        return this.featureList;
    }
}
