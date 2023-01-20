
package com.ebuozturk.productcategory.service;

import com.ebuozturk.productcategory.converter.StoreConverter;
import com.ebuozturk.productcategory.dto.store.CreateStoreRequest;
import com.ebuozturk.productcategory.dto.store.StoreDto;
import com.ebuozturk.productcategory.dto.store.UpdateStoreRequest;
import com.ebuozturk.productcategory.exception.NotFoundException;
import com.ebuozturk.productcategory.model.Seller;
import com.ebuozturk.productcategory.model.Store;
import com.ebuozturk.productcategory.repository.StoreRepository;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class StoreService
{
    private final StoreRepository storeRepository;
    private final SellerService sellerService;
    private final StoreConverter storeConverter;

    public StoreService(StoreRepository storeRepository, SellerService sellerService, StoreConverter storeConverter) {
        this.storeRepository = storeRepository;
        this.sellerService = sellerService;
        this.storeConverter = storeConverter;
    }

    public StoreDto createStore(CreateStoreRequest request) {
        Set<Seller> sellerList = new HashSet<>();
        getRequestSellers(request.sellerIds()).forEach(seller -> sellerList.add(seller));

        Store store = new Store(request.name(), sellerList);
        store.setCreatedAt(LocalDateTime.now());
        store.setUpdatedAt(LocalDateTime.now());

        return this.storeConverter.convert((Store)this.storeRepository.save(store));
    }

    public StoreDto updateStore(String id, UpdateStoreRequest request) {
        Store existStore = findById(id);
        Set<Seller> sellerList = getRequestSellers(request.sellerIds());

        Store store = new Store(existStore.getId(), request.name(), sellerList, existStore.getProducts());
        store.setCreatedAt(store.getCreatedAt());
        store.setUpdatedAt(LocalDateTime.now());

        return this.storeConverter.convert((Store)this.storeRepository.save(store));
    }

    public List<StoreDto> getAll() {
        return this.storeConverter.convert(this.storeRepository.findAll());
    }

    public StoreDto getById(String id) {
        return this.storeConverter.convert(findById(id));
    }

    public Boolean deleteById(String id) {
        Store store = findById(id);
        try {
            this.storeRepository.delete(store);
            return Boolean.valueOf(true);
        } catch (Exception e) {
            return Boolean.valueOf(false);
        }
    }
    protected Store findById(String id) {
        return (Store)this.storeRepository.findById(id).orElseThrow(() -> new NotFoundException("The store could not be found by following id: " + id));
    }

    private Set<Seller> getRequestSellers(List<String> sellerIds) {
        if (sellerIds.size() > 0) {
            Objects.requireNonNull(this.sellerService); return (Set<Seller>)sellerIds.stream().map(this.sellerService::findById).collect(Collectors.toSet());
        }
        throw new NotFoundException("Minimum 1 seller required!");
    }
}
