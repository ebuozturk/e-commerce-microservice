
package com.ebuozturk.productcategory.controller;
import com.ebuozturk.productcategory.dto.seller.CreateSellerRequest;
import com.ebuozturk.productcategory.dto.seller.SellerDto;
import com.ebuozturk.productcategory.dto.seller.UpdateSellerRequest;
import com.ebuozturk.productcategory.service.SellerService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin({"*"})
@RestController
@RequestMapping({"v1/seller"})
public class SellerController {
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }
    private final SellerService sellerService;
    @GetMapping
    public ResponseEntity<List<SellerDto>> getAll() {
        return ResponseEntity.ok(this.sellerService.getAll());
    }
    @GetMapping({"{id}"})
    public ResponseEntity<SellerDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(this.sellerService.getById(id));
    }
    @PostMapping
    public ResponseEntity<SellerDto> createSeller(@Valid @RequestBody CreateSellerRequest request) {
        return new ResponseEntity(this.sellerService.createSeller(request), HttpStatus.CREATED);
    }
    @PutMapping({"{id}"})
    public ResponseEntity<SellerDto> updateSeller(@PathVariable String id, @Valid @RequestBody UpdateSellerRequest request) {
        return ResponseEntity.ok(this.sellerService.updateSeller(id, request));
    }
    @DeleteMapping({"{id}"})
    public ResponseEntity<Boolean> deleteSeller(@PathVariable String id) {
        return ResponseEntity.ok(this.sellerService.deleteById(id));
    }
}
