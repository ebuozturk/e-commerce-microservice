
package com.ebuozturk.productcategory.controller;
import com.ebuozturk.productcategory.dto.category.CategoryDto;
import com.ebuozturk.productcategory.dto.category.CreateCategoryRequest;
import com.ebuozturk.productcategory.dto.category.UpdateCategoryRequest;
import com.ebuozturk.productcategory.service.CategoryService;
import java.util.List;
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
@RequestMapping({"/v1/category"})
public class CategoryController {
    public CategoryController(CategoryService service) {
        this.service = service;
    }
    private final CategoryService service;
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(this.service.getAllCategories());
    }

    @GetMapping({"/getAllWithParent"})
    public ResponseEntity<List<CategoryDto>> getAllCategoriesWithParent() {
        return ResponseEntity.ok(this.service.getAllCategoriesWithParent());
    }

    @GetMapping({"/getAllWithChild"})
    public ResponseEntity<List<CategoryDto>> getAllCategoriesWithChild() {
        return ResponseEntity.ok(this.service.getAllCategoriesWithChild());
    }
    @GetMapping({"/getAllMainCategories"})
    public ResponseEntity<List<CategoryDto>> getAllMainCategories() {
        return ResponseEntity.ok(this.service.getAllMainCategories());
    }
    @GetMapping({"/getAllChildCategories/{parentId}"})
    public ResponseEntity<List<CategoryDto>> getAllMainCategories(@PathVariable String parentId) {
        return ResponseEntity.ok(this.service.getAllChildCategoriesByParentId(parentId));
    }
    @GetMapping({"{id}"})
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.service.getCategoryById(id));
    }
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CreateCategoryRequest request) {
        return ResponseEntity.ok(this.service.createCategory(request));
    }
    @PutMapping({"{id}"})
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable String id, @RequestBody UpdateCategoryRequest request) {
        return ResponseEntity.ok(this.service.updateCategory(id, request));
    }
    @DeleteMapping({"{id}"})
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") String id) {
        this.service.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}
