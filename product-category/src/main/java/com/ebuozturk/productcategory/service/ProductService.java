
package com.ebuozturk.productcategory.service;

import com.ebuozturk.productcategory.converter.ProductConverter;
import com.ebuozturk.productcategory.dto.category.CategoryDto;
import com.ebuozturk.productcategory.dto.product.CreateProductRequest;
import com.ebuozturk.productcategory.dto.product.ProductDto;
import com.ebuozturk.productcategory.dto.product.UpdateProductRequest;
import com.ebuozturk.productcategory.exception.NotFoundException;
import com.ebuozturk.productcategory.model.Feature;
import com.ebuozturk.productcategory.model.MainProduct;
import com.ebuozturk.productcategory.model.Product;
import com.ebuozturk.productcategory.model.Store;
import com.ebuozturk.productcategory.model.es.ProductEs;
import com.ebuozturk.productcategory.repository.ProductRepository;
import com.ebuozturk.productcategory.service.es.ProductEsService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService
{
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final MainProductService mainProductService;
    private final FeatureService featureService;
    private final StoreService storeService;
    private final CategoryService categoryService;
    private final ProductEsService productEsService;

    public ProductService(ProductRepository productRepository, ProductConverter productConverter, MainProductService mainProductService, FeatureService featureService, StoreService storeService, CategoryService categoryService, ProductEsService productEsService) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
        this.mainProductService = mainProductService;
        this.featureService = featureService;
        this.storeService = storeService;
        this.categoryService = categoryService;
        this.productEsService = productEsService;
    }


    public ProductDto createProduct(CreateProductRequest request) {
        MainProduct mainProduct = this.mainProductService.findById(request.mainProductId());

        Set<Feature> featureList = request.featureList().stream().map(id -> this.featureService.findById(id)).collect(Collectors.toSet());
        Store store = this.storeService.findById(request.storeId());
        Product createdProduct = (Product)this.productRepository.save(new Product(mainProduct, request
                .unitsInStock(), request
                .unitPrice().doubleValue(), request
                .quantityPerUnit(), featureList, store));

        createdProduct.setCreatedAt(LocalDateTime.now());
        createdProduct.setUpdatedAt(LocalDateTime.now());
        this.productEsService.createProductEs(new ProductEs(createdProduct.getId(),
                mainProduct.getName(),
                featureList
                        .stream()
                        .map(Feature::getName)
                        .collect(Collectors.toList()),
                createdProduct.getUnitPrice()
                ));
        return this.productConverter.convert(createdProduct);
    }
    public ProductDto updateProduct(String id, UpdateProductRequest request) {
        Product existProduct = findById(id);

        MainProduct mainProduct = existProduct.getMainProduct().getId().equals(request.mainProductId()) ? existProduct.getMainProduct() : this.mainProductService.findById(request.mainProductId());


        Objects.requireNonNull(this.featureService); Set<Feature> featureList = (Set<Feature>)request.featureList().stream().map(this.featureService::findById).collect(Collectors.toSet());


        Product product = new Product(existProduct.getId(),
                mainProduct, request.unitsInStock(),
                request.unitPrice().doubleValue(),
                request.quantityPerUnit(),
                featureList,
                existProduct.getStore());
        product.setCreatedAt(existProduct.getCreatedAt());
        product.setUpdatedAt(LocalDateTime.now());

        return this.productConverter.convert((Product)this.productRepository.save(product));
    }


    public List<ProductDto> getAll() {
        return this.productConverter.convert(this.productRepository.findAll());
    }
    public ProductDto getById(String id) {
        return this.productConverter.convert(findById(id));
    }
    public ProductDto getProductByMainProductAndFeature(String mainProductId, List<String> featureList) {
        return this.productConverter.convert(findProductByMainProductIdAndFeatureIds(mainProductId, featureList));
    }
    public List<ProductDto> getAllProductsByCategoryId(String categoryId) {
        List<String> ids = (List<String>)this.categoryService.getAllCategoriesByCategoryId(categoryId).stream().map(CategoryDto::getId).collect(Collectors.toList());
        System.out.println(ids);
        return this.productConverter.convert(this.productRepository.findAllByCategoryIdList(ids));
    }

    public Boolean deleteById(String id) {
        Product product = findById(id);
        try {
            this.productRepository.delete(product);
            this.productEsService.deleteProductEs(product.getId());
            return Boolean.valueOf(true);
        } catch (Exception e) {
            return Boolean.valueOf(false);
        }
    }
    protected Product findProductByMainProductIdAndFeatureIds(String mainProductId, List<String> featureIdList) {
        return (Product)this.productRepository.findProductByFeatures(featureIdList, mainProductId).orElseThrow(() -> new NotFoundException("The product could not be found by passing parameters."));
    }


    protected Product findById(String id) {
        return (Product)this.productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("The product could not be found by folowing id: " + id));
    }

    @Transactional
    public ProductDto updateUnitsInStock(String id, Integer unitsInStock) {
        this.productRepository.updateUnitsInStock(id, unitsInStock);
        return this.productConverter.convert(findById(id));
    }
}
