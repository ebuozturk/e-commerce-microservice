
package com.ebuozturk.productcategory.service;

import com.ebuozturk.productcategory.converter.SellerConverter;
import com.ebuozturk.productcategory.dto.seller.CreateSellerRequest;
import com.ebuozturk.productcategory.dto.seller.SellerDto;
import com.ebuozturk.productcategory.dto.seller.UpdateSellerRequest;
import com.ebuozturk.productcategory.exception.NotFoundException;
import com.ebuozturk.productcategory.model.Seller;
import com.ebuozturk.productcategory.repository.SellerRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class SellerService
{
    private final SellerRepository sellerRepository;
    private final SellerConverter sellerConverter;

    public SellerService(SellerRepository sellerRepository, SellerConverter sellerConverter) {
        this.sellerRepository = sellerRepository;
        this.sellerConverter = sellerConverter;
    }
    public SellerDto createSeller(CreateSellerRequest request) {
        Seller seller = new Seller(request.firstName(), request.lastName(), request.email());
        seller.setCreatedAt(LocalDateTime.now());
        seller.setUpdatedAt(LocalDateTime.now());
        return this.sellerConverter.convert((Seller)this.sellerRepository.save(seller));
    }
    public SellerDto updateSeller(String id, UpdateSellerRequest request) {
        Seller seller = findById(id);
        Seller updateSeller = new Seller(seller.getId(), request.firstName(), request.lastName(), request.email());
        updateSeller.setCreatedAt(seller.getCreatedAt());
        updateSeller.setUpdatedAt(LocalDateTime.now());
        return this.sellerConverter.convert((Seller)this.sellerRepository.save(updateSeller));
    }
    public List<SellerDto> getAll() {
        return this.sellerConverter.convert(this.sellerRepository.findAll());
    }

    public SellerDto getById(String id) {
        return this.sellerConverter.convert(findById(id));
    }

    public Boolean deleteById(String id) {
        Seller seller = findById(id);
        try {
            this.sellerRepository.delete(seller);
            return Boolean.valueOf(true);
        } catch (Exception e) {
            return Boolean.valueOf(false);
        }
    }
    protected Seller findById(String id) {
        return (Seller)this.sellerRepository.findById(id).orElseThrow(() -> new NotFoundException("The seller with " + id + " does not exist!"));
    }
}
