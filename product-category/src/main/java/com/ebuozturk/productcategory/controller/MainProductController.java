
package com.ebuozturk.productcategory.controller;

import com.ebuozturk.productcategory.dto.mainproduct.CreateMainProductRequest;
import com.ebuozturk.productcategory.dto.mainproduct.MainProductDto;
import com.ebuozturk.productcategory.dto.mainproduct.UpdateMainProductRequest;
import com.ebuozturk.productcategory.service.MainProductService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin({"*"})
@RestController
@RequestMapping({"/v1/mainProduct"})
public class MainProductController {
    private final MainProductService service;

    public MainProductController(MainProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<MainProductDto>> getAllProducts() {
        return ResponseEntity.ok(this.service.getAllProducts());
    }

    @GetMapping({"{id}"})
    public ResponseEntity<MainProductDto> getProductById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.service.getProductById(id));
    }
    @GetMapping({"/category/{categoryId}"})
    public ResponseEntity<List<MainProductDto>> getAllByCategoryId(@PathVariable String categoryId) {
        return ResponseEntity.ok(this.service.getAllProductsByCategoryId(categoryId));
    }


    @PostMapping
    public ResponseEntity<MainProductDto> createProduct(@RequestBody CreateMainProductRequest request) {
        return new ResponseEntity(this.service.createProduct(request), HttpStatus.CREATED);
    }

    @PutMapping({"{id}"})
    public ResponseEntity<MainProductDto> updateProduct(@PathVariable String id, @RequestBody UpdateMainProductRequest request) {
        return ResponseEntity.ok(this.service.updateProduct(id, request));
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String id) {
        this.service.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
