package com.ebuozturk.images.converter;

import com.ebuozturk.images.dto.ProductImageDto;
import com.ebuozturk.images.model.ProductImage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductImageConverter {

    @Value("${gateway.server.port}")
    private String gatewayPort;

    public ProductImageDto convert(ProductImage productImage){
        return new ProductImageDto(productImage.getKey(),
                                    productImage.getProductId(),
                                    productImage.getPosition(),
                                    "http://localhost:"+gatewayPort+productImage.getSrc());
    }

    public List<ProductImageDto> convert(List<ProductImage> productImageList){
        return productImageList.stream().map(this::convert).collect(Collectors.toList());
    }
}
