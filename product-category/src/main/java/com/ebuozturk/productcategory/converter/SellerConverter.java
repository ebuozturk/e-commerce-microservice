package com.ebuozturk.productcategory.converter;

import com.ebuozturk.productcategory.dto.seller.SellerDto;
import com.ebuozturk.productcategory.model.Seller;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;


@Component
public class SellerConverter
{
    public SellerDto convert(Seller seller) {
        return new SellerDto(seller.getId(), seller.getFirstName(), seller.getLastName(), seller.getEmail());
    }

    public List<SellerDto> convert(List<Seller> sellers) {
        return (List<SellerDto>)sellers.stream().map(this::convert).collect(Collectors.toList());
    }
    public List<SellerDto> convert(Set<Seller> sellers) {
        return (List<SellerDto>)sellers.stream().map(this::convert).collect(Collectors.toList());
    }
}
