package com.ebuozturk.images.dto;

public class UpdateProductImageRequest {

    private String productId;
    private Integer position;
    private String src;

    public UpdateProductImageRequest() {
    }

    public UpdateProductImageRequest(String productId, Integer position, String src) {
        this.productId = productId;
        this.position = position;
        this.src = src;
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
}
