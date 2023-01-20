
package com.ebuozturk.productcategory.converter;

import com.ebuozturk.productcategory.dto.mainproduct.MainProductDto;
import com.ebuozturk.productcategory.model.MainProduct;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class MainProductConverter
{
    private final CategoryConverter categoryConverter;
    private final BrandConverter brandConverter;

    public MainProductConverter(CategoryConverter categoryConverter, BrandConverter brandConverter) {
        this.categoryConverter = categoryConverter;
        this.brandConverter = brandConverter;
    }

    public MainProductDto convert(MainProduct mainProduct) {
        return new MainProductDto(mainProduct.getId(), mainProduct
                .getName(), mainProduct
                .getDescription(), this.brandConverter
                .convert(mainProduct.getBrand()), mainProduct
                .getWarrantyType(), this.categoryConverter
                .convertToCategoryWithParentDto(mainProduct.getCategory()));
    }


    public List<MainProductDto> convert(List<MainProduct> mainProductList) {
        return (List<MainProductDto>)mainProductList.stream().map(this::convert).collect(Collectors.toList());
    }
}
