package com.ebuozturk.productcategory.controller;

import com.ebuozturk.productcategory.dto.category.CategoryDto;
import com.ebuozturk.productcategory.dto.category.CreateCategoryRequest;
import com.ebuozturk.productcategory.dto.category.UpdateCategoryRequest;
import com.ebuozturk.productcategory.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/category")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(service.getAllCategories());
    }
    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") String id){
        return ResponseEntity.ok(service.getCategoryById(id));
    }
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CreateCategoryRequest request){
        return ResponseEntity.ok(service.createCategory(request));
    }
    @PutMapping("{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable String id,@RequestBody UpdateCategoryRequest request){
        return ResponseEntity.ok(service.updateCategory(id,request));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") String id){
        service.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}
