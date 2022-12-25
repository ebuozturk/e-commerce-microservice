package com.ebuozturk.productcategory.service;

import com.ebuozturk.productcategory.converter.ProductConverter;
import com.ebuozturk.productcategory.dto.product.CreateProductRequest;
import com.ebuozturk.productcategory.dto.product.ProductDto;
import com.ebuozturk.productcategory.dto.product.UpdateProductRequest;
import com.ebuozturk.productcategory.exception.ProductNotFoundException;
import com.ebuozturk.productcategory.model.Category;
import com.ebuozturk.productcategory.model.Product;
import com.ebuozturk.productcategory.model.es.ProductEs;
import com.ebuozturk.productcategory.repository.ProductRepository;
import com.ebuozturk.productcategory.service.es.ProductEsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final CategoryService categoryService;
    private final ProductEsService productEsService;


    public ProductService(ProductRepository productRepository, ProductConverter productConverter, CategoryService categoryService, ProductEsService productEsService) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
        this.categoryService = categoryService;
        this.productEsService = productEsService;
    }

    public ProductDto getProductById(String id){
        return productConverter.convert(findById(id));
    }
    public List<ProductDto> getAllProducts(){
        return productConverter.convert(productRepository.findAll());
    }

    public List<ProductDto> getProductsByCategoryId(String categoryId){
        categoryService.findById(categoryId);
        return productConverter.convert(productRepository.findByCategory_id(categoryId));
    }


    public ProductDto createProduct(final CreateProductRequest request){
        Category category = categoryService.findById(request.getCategoryId());
        Product createdProduct = productRepository.save(new Product(request.getName(),request.getDescription(),
                request.getUnitPrice(),request.getQuantityPerUnit(), request.getUnitsInStock(),category));

        productEsService.createProductEs(new ProductEs(createdProduct.getId(),createdProduct.getName()));

        return productConverter.convert(createdProduct);
    }
    public ProductDto updateProduct(final String id,final UpdateProductRequest request){
        Category category = categoryService.findById(request.getCategoryId());
        findById(id);
        return productConverter.convert(productRepository.save(new Product(id,request.getName(),
                request.getUnitPrice(),request.getQuantityPerUnit(), request.getUnitsInStock(),category)));
    }

    public void deleteProduct(String id){
        if(doesProductExist(id)){
           productRepository.deleteById(id);
        }else
            throw new ProductNotFoundException("Product couldn't found by following id: "+id);
    }
    protected Boolean doesProductExist(String id){
        return productRepository.existsById(id);
    }
    protected Product findById(String id){
        return productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product couldn't found by following id: "+id));
    }
}
