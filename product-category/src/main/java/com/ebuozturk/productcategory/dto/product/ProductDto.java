package com.ebuozturk.productcategory.dto.product;

import com.ebuozturk.productcategory.dto.feature.FeatureDto;
import com.ebuozturk.productcategory.dto.mainproduct.MainProductDto;
import com.ebuozturk.productcategory.dto.store.StoreDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.stream.Collectors;

public record ProductDto(String id, MainProductDto mainProduct, Integer unitsInStock, Double unitPrice, Integer quantityPerUnit, List<FeatureDto> featureList, StoreDto store) {
    public ProductDto(@JsonProperty("id") String id, @JsonProperty("mainProduct") MainProductDto mainProduct, @JsonProperty("unitsInStock") Integer unitsInStock, @JsonProperty("unitPrice") Double unitPrice, @JsonProperty("quantityPerUnit") Integer quantityPerUnit, @JsonProperty("featureList") List<FeatureDto> featureList, @JsonProperty("store") StoreDto store) {
        this.id = id;
        this.mainProduct = mainProduct;
        this.unitsInStock = unitsInStock;
        this.unitPrice = unitPrice;
        this.quantityPerUnit = quantityPerUnit;
        this.featureList = featureList;
        this.store = store;
    }

    public String getName() {
        if (this.mainProduct.getCategory().getName().equalsIgnoreCase("smart phone")) {
            String var10000 = this.mainProduct.getName();
            return var10000 + (String)this.featureList.stream().map((featureDto) -> {
                return " " + featureDto.name();
            }).collect(Collectors.joining());
        } else {
            return this.mainProduct.getName();
        }
    }

    @JsonProperty("id")
    public String id() {
        return this.id;
    }

    @JsonProperty("mainProduct")
    public MainProductDto mainProduct() {
        return this.mainProduct;
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
    public List<FeatureDto> featureList() {
        return this.featureList;
    }

    @JsonProperty("store")
    public StoreDto store() {
        return this.store;
    }
}
