package com.ebuozturk.productcategory.controller;


import com.ebuozturk.productcategory.dto.product.CreateProductRequest;
import com.ebuozturk.productcategory.dto.product.ProductDto;
import com.ebuozturk.productcategory.dto.product.UpdateProductRequest;
import com.ebuozturk.productcategory.model.es.ProductEs;
import com.ebuozturk.productcategory.service.ProductService;
import com.ebuozturk.productcategory.service.es.ProductEsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/product")
public class ProductController {

    private final ProductService service;
    private final ProductEsService esService;

    public ProductController(ProductService service, ProductEsService esService) {
        this.service = service;
        this.esService = esService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return ResponseEntity.ok(service.getAllProducts());
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") String id){
        return ResponseEntity.ok(service.getProductById(id));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<ProductDto>> getProductsByCategoryId(@PathVariable("id") String id){
        return ResponseEntity.ok(service.getProductsByCategoryId(id));
    }
    @GetMapping("/producteses")
    public ResponseEntity<List<ProductEs>> getAllProductEs(){
        return ResponseEntity.ok(esService.getAllProducts());
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<Page<ProductEs>> searchProductsByName(@PathVariable String name, Pageable pageable){
        return ResponseEntity.ok(esService.getByNameFuzzy(name,pageable));
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody CreateProductRequest request){
        return new ResponseEntity<>(service.createProduct(request), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String id, @RequestBody UpdateProductRequest request){
        return ResponseEntity.ok(service.updateProduct(id,request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String id){
        service.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

}
