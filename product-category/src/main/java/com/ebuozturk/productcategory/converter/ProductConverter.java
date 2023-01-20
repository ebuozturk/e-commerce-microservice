
package com.ebuozturk.productcategory.converter;

import com.ebuozturk.productcategory.dto.product.ProductDto;
import com.ebuozturk.productcategory.model.Product;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;


@Component
public class ProductConverter
{
    private final MainProductConverter mainProductConverter;
    private final FeatureConverter featureConverter;
    private final StoreConverter storeConverter;

    public ProductConverter(MainProductConverter mainProductConverter, FeatureConverter featureConverter, StoreConverter storeConverter) {
        this.mainProductConverter = mainProductConverter;
        this.featureConverter = featureConverter;
        this.storeConverter = storeConverter;
    }

    public ProductDto convert(Product product) {
        return new ProductDto(product.getId(), this.mainProductConverter
                .convert(product.getMainProduct()), product
                .getUnitsInStock(),
                Double.valueOf(product.getUnitPrice()), product
                .getQuantityPerUnit(), this.featureConverter
                .convert(product.getFeatures()), this.storeConverter
                .convert(product.getStore()));
    }
    public List<ProductDto> convert(List<Product> productList) {
        return (List<ProductDto>)productList.stream().map(this::convert).collect(Collectors.toList());
    }
    public List<ProductDto> convert(Set<Product> productSet) {
        return (List<ProductDto>)productSet.stream().map(this::convert).collect(Collectors.toList());
    }
}
