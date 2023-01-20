package com.ebuozturk.productcategory.converter;

import com.ebuozturk.productcategory.dto.store.StoreDto;
import com.ebuozturk.productcategory.model.Store;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreConverter {
    @Autowired
    private SellerConverter sellerConverter;

    public StoreConverter() {
    }

    public StoreDto convert(Store store) {
        return new StoreDto(store.getId(), store.getName(), this.sellerConverter.convert(store.getSellers()));
    }

    public List<StoreDto> convert(List<Store> storeList) {
        return (List)storeList.stream().map(this::convert).collect(Collectors.toList());
    }
}
