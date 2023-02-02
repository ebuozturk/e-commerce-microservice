
package com.ebuozturk.productcategory.controller;

import com.ebuozturk.productcategory.dto.product.CreateProductRequest;
import com.ebuozturk.productcategory.dto.product.GetProductByMainProductAndFeatureIdsRequest;
import com.ebuozturk.productcategory.dto.product.ProductDto;
import com.ebuozturk.productcategory.dto.product.UpdateProductRequest;
import com.ebuozturk.productcategory.model.es.ProductEs;
import com.ebuozturk.productcategory.service.ProductService;
import com.ebuozturk.productcategory.service.es.ProductEsService;

import java.util.List;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin({"*"})
@RestController
@RequestMapping({"v1/product"})
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService, ProductEsService esService) {
        this.productService = productService;
        this.esService = esService;
    }

    private final ProductEsService esService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll() {
        return ResponseEntity.ok(this.productService.getAll());
    }

    @GetMapping({"{id}"})
    public ResponseEntity<ProductDto> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.productService.getById(id));
    }

    @GetMapping({"/category/{categoryId}"})
    public ResponseEntity<List<ProductDto>> getAllByCategoryId(@PathVariable("categoryId") String categoryId) {
        return ResponseEntity.ok(this.productService.getAllProductsByCategoryId(categoryId));
    }

    @GetMapping({"/es/products"})
    public ResponseEntity<List<ProductEs>> getAllProductEs() {
        return ResponseEntity.ok(this.esService.getAllProducts());
    }

    @GetMapping({"/search/{name}"})
    public ResponseEntity<List<ProductEs>> searchProductsByName(@PathVariable("name") String query) {
        return ResponseEntity.ok(this.esService.getByQuery(query));
    }

    @PostMapping({"/getProductByMainProductIdAndFeatureIds"})
    public ResponseEntity<ProductDto> getProductByMainProductAndFeatureIds(@Valid @RequestBody GetProductByMainProductAndFeatureIdsRequest request) {
        return ResponseEntity.ok(this.productService.getProductByMainProductAndFeature(request.mainProductId(), request.featureIdList()));
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody CreateProductRequest request) {
        return new ResponseEntity(this.productService.createProduct(request), HttpStatus.CREATED);
    }

    @PutMapping({"{id}"})
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") String id, @Valid @RequestBody UpdateProductRequest request) {
        return ResponseEntity.ok(this.productService.updateProduct(id, request));
    }

    @PutMapping({"/updateUnitsInStock/{id}"})
    public ResponseEntity<ProductDto> updateProductUnitsInStock(@PathVariable("id") String id, @RequestParam("unitsInStock") Integer unitsInStock) {
        return ResponseEntity.ok(this.productService.updateUnitsInStock(id, unitsInStock));
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.productService.deleteById(id));
    }
}
