
package com.ebuozturk.productcategory.controller;
import com.ebuozturk.productcategory.dto.brand.BrandDto;
import com.ebuozturk.productcategory.dto.brand.CreateBrandRequest;
import com.ebuozturk.productcategory.dto.brand.UpdateBrandRequest;
import com.ebuozturk.productcategory.service.BrandService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin({"*"})
@RestController
@RequestMapping({"v1/brand"})
public class BrandController {
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }
    private final BrandService brandService;
    @GetMapping
    public ResponseEntity<List<BrandDto>> getAllBrands() {
        return ResponseEntity.ok(this.brandService.getAll());
    }
    @GetMapping({"/{id}"})
    public ResponseEntity<BrandDto> getBrandById(@PathVariable String id) {
        return ResponseEntity.ok(this.brandService.getById(id));
    }
    @PostMapping
    public ResponseEntity<BrandDto> createBrand(@Valid @RequestBody CreateBrandRequest request) {
        return new ResponseEntity(this.brandService.createBrand(request), HttpStatus.CREATED);
    }
    @PutMapping({"/{id}"})
    public ResponseEntity<BrandDto> updateBrand(@PathVariable String id, @Valid @RequestBody UpdateBrandRequest request) {
        return ResponseEntity.ok(this.brandService.updateBrand(id, request));
    }
}
