package com.ebuozturk.basket.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Product(
        String id,
        String name,
        String description,
        Double unitPrice,
        Integer quantityPerUnit,
        Integer unitsInStock
) {
    public Product( @JsonProperty("id") String id,
                    @JsonProperty("name") String name,
                    @JsonProperty("description") String description,
                    @JsonProperty("unitPrice") Double unitPrice,
                    @JsonProperty("quantityPerUnit") Integer quantityPerUnit,
                    @JsonProperty("unitsInStock") Integer unitsInStock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.quantityPerUnit = quantityPerUnit;
        this.unitsInStock = unitsInStock;
    }

    @JsonProperty("id")
    public String id() {
        return id;
    }

    @JsonProperty("name")
    public String name() {
        return name;
    }

    @JsonProperty("description")
    public String description() {
        return description;
    }

    @JsonProperty("unitPrice")
    public Double unitPrice() {
        return unitPrice;
    }

    @JsonProperty("quantityPerUnit")
    public Integer quantityPerUnit() {
        return quantityPerUnit;
    }

    @JsonProperty("unitsInStock")
    public Integer unitsInStock() {
        return unitsInStock;
    }
}
