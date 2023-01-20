
package com.ebuozturk.productcategory.service;

import com.ebuozturk.productcategory.converter.MainProductConverter;
import com.ebuozturk.productcategory.dto.mainproduct.CreateMainProductRequest;
import com.ebuozturk.productcategory.dto.mainproduct.MainProductDto;
import com.ebuozturk.productcategory.dto.mainproduct.UpdateMainProductRequest;
import com.ebuozturk.productcategory.exception.ProductNotFoundException;
import com.ebuozturk.productcategory.model.Brand;
import com.ebuozturk.productcategory.model.Category;
import com.ebuozturk.productcategory.model.MainProduct;
import com.ebuozturk.productcategory.model.WarrantyType;
import com.ebuozturk.productcategory.repository.MainProductRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;


@Service
public class MainProductService
{
    private final MainProductRepository mainProductRepository;
    private final MainProductConverter mainProductConverter;
    private final CategoryService categoryService;
    private final BrandService brandService;

    public MainProductService(MainProductRepository mainProductRepository, MainProductConverter mainProductConverter, CategoryService categoryService, BrandService brandService) {
        this.mainProductRepository = mainProductRepository;
        this.mainProductConverter = mainProductConverter;
        this.categoryService = categoryService;
        this.brandService = brandService;
    }

    public MainProductDto getProductById(String id) {
        return this.mainProductConverter.convert(findById(id));
    }
    public List<MainProductDto> getAllProducts() {
        return this.mainProductConverter.convert(this.mainProductRepository.findAll());
    }

    public List<MainProductDto> getProductsByCategoryId(String categoryId) {
        this.categoryService.findById(categoryId);
        return this.mainProductConverter.convert(this.mainProductRepository.findByCategory_id(categoryId));
    }
    public List<MainProductDto> getAllProductsByCategoryId(String categoryId) {
        return this.mainProductConverter.convert(this.mainProductRepository.findByCategory_id(categoryId));
    }


    public MainProductDto createProduct(CreateMainProductRequest request) {
        Category category = this.categoryService.findById(request.getCategoryId());
        Brand brand = this.brandService.findById(request.getBrandId());
        WarrantyType warrantyType = Objects.isNull(request.getWarrantyType()) ? WarrantyType.NULL : WarrantyType.getTypeByName(request.getWarrantyType());
        MainProduct createdMainProduct = (MainProduct)this.mainProductRepository.save(new MainProduct(request.getName(), request.getDescription(), category, brand, warrantyType));


        return this.mainProductConverter.convert(createdMainProduct);
    }

    public MainProductDto updateProduct(String id, UpdateMainProductRequest request) {
        MainProduct existMainProduct = findById(id);
        Category category = this.categoryService.findById(request.getCategoryId());
        Brand brand = this.brandService.findById(request.getBrandId());
        WarrantyType warrantyType = WarrantyType.getTypeByName(request.getWarrantyType());
        MainProduct mainProduct = new MainProduct(existMainProduct.getId(), request.getName(), request.getDescription(), category, brand, warrantyType);

        mainProduct.setCreatedAt(existMainProduct.getCreatedAt());
        mainProduct.setUpdatedAt(LocalDateTime.now());
        return this.mainProductConverter.convert((MainProduct)this.mainProductRepository.save(mainProduct));
    }

    public void deleteProduct(String id) {
        if (doesProductExist(id).booleanValue()) {
            this.mainProductRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException("Product couldn't found by following id: " + id);
        }
    } protected Boolean doesProductExist(String id) {
    return Boolean.valueOf(this.mainProductRepository.existsById(id));
}

    protected MainProduct findById(String id) {
        return (MainProduct)this.mainProductRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product couldn't found by following id: " + id));
    }
}
