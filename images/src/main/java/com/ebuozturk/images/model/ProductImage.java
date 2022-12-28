package com.ebuozturk.images.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProductImage {

    @Id
    private String key;
    private String productId;
    private Integer position;
    private String src;

    public ProductImage() {
    }

    public ProductImage(String key, String productId, Integer position, String src) {
        this.key = key;
        this.productId = productId;
        this.position = position;
        this.src = src;
    }

    public String getKey() {
        return key;
    }

    public String getProductId() {
        return productId;
    }

    public Integer getPosition() {
        return position;
    }

    public String getSrc() {
        return src;
    }

    @Override
    public String toString() {
        return "ProductImage{" +
                "key='" + key + '\'' +
                ", productId='" + productId + '\'' +
                ", position=" + position +
                ", src='" + src + '\'' +
                '}';
    }
}
