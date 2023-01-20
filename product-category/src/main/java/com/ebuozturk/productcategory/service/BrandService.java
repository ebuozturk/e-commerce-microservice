
package com.ebuozturk.productcategory.service;

import com.ebuozturk.productcategory.converter.BrandConverter;
import com.ebuozturk.productcategory.dto.brand.BrandDto;
import com.ebuozturk.productcategory.dto.brand.CreateBrandRequest;
import com.ebuozturk.productcategory.dto.brand.UpdateBrandRequest;
import com.ebuozturk.productcategory.exception.BrandNotFoundException;
import com.ebuozturk.productcategory.model.Brand;
import com.ebuozturk.productcategory.repository.BrandRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BrandService {
    private final BrandRepository brandRepository;
    private final BrandConverter brandConverter;

    public BrandService(BrandRepository brandRepository, BrandConverter brandConverter) {
        this.brandRepository = brandRepository;
        this.brandConverter = brandConverter;
    }

    public BrandDto createBrand(CreateBrandRequest request) {
        Brand brand = new Brand(request.name());
        brand.setCreatedAt(LocalDateTime.now());
        brand.setUpdatedAt(LocalDateTime.now());
        return this.brandConverter.convert((Brand)this.brandRepository.save(brand));
    }

    public BrandDto updateBrand(String id, UpdateBrandRequest request) {
        Brand brand = this.findById(id);
        if (!request.name().equals(brand.getName())) {
            Brand updateBrand = new Brand(brand.getId(), request.name());
            updateBrand.setCreatedAt(brand.getCreatedAt());
            updateBrand.setUpdatedAt(LocalDateTime.now());
            brand = (Brand)this.brandRepository.save(updateBrand);
        }

        return this.brandConverter.convert(brand);
    }

    public List<BrandDto> getAll() {
        return this.brandConverter.convert(this.brandRepository.findAll());
    }

    public BrandDto getById(String id) {
        return this.brandConverter.convert(this.findById(id));
    }

    protected Brand findById(String id) {
        return (Brand)this.brandRepository.findById(id).orElseThrow(() -> {
            return new BrandNotFoundException("The Brand couldn't be found by following id: " + id);
        });
    }
}
