
package com.ebuozturk.productcategory.converter;

import com.ebuozturk.productcategory.dto.brand.BrandDto;
import com.ebuozturk.productcategory.model.Brand;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;



@Component
public class BrandConverter
{
    public BrandDto convert(Brand brand) {
        return new BrandDto(brand.getId(), brand.getName());
    }

    public List<BrandDto> convert(Set<Brand> brandSet) {
        return (List<BrandDto>)brandSet.stream().map(this::convert).collect(Collectors.toList());
    }
    public List<BrandDto> convert(List<Brand> brandSet) {
        return (List<BrandDto>)brandSet.stream().map(this::convert).collect(Collectors.toList());
    }
}
