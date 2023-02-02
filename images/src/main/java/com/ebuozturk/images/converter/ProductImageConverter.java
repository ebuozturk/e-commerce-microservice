package com.ebuozturk.images.converter;

import com.ebuozturk.images.dto.ProductImageDto;
import com.ebuozturk.images.model.ProductImage;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProductImageConverter {
    @Value("${gateway.server.port}")
    private String gatewayPort;

    public ProductImageConverter() {
    }

    public ProductImageDto convert(ProductImage productImage) {
        String var10002 = productImage.getKey();
        String var10003 = productImage.getProductId();
        Integer var10004 = productImage.getPosition();
        String var10005 = this.gatewayPort;
        return new ProductImageDto(var10002, var10003, var10004, "http://localhost:" + var10005 + productImage.getSrc());
    }

    public List<ProductImageDto> convert(List<ProductImage> productImageList) {
        return (List)productImageList.stream().map(this::convert).collect(Collectors.toList());
    }
}
