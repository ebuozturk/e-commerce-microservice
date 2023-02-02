package com.ebuozturk.productcategory.dto.product.es;

import com.ebuozturk.productcategory.dto.mainproduct.MainProductDto;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.util.List;

public class ProductEsDto {

    private String id;
    private String productId;
    private String name;
    private List<String> featureList;


    public String getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public List<String> getFeatureList() {
        return featureList;
    }

    public String getMainProductId() {
        return "";
    }

    public Integer getUnitsInStock() {
        return 0;
    }

    public Double getUnitPrice() {
        return 0.0;
    }

    public Integer getQuantityPerUnit() {
        return 0;
    }

    public String getStoreId() {
        return "";
    }

    public MainProductDto getMainProduct() {
        return null;
    }
}
