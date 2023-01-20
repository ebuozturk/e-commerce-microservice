
package com.ebuozturk.productcategory.controller;
import com.ebuozturk.productcategory.dto.store.CreateStoreRequest;
import com.ebuozturk.productcategory.dto.store.StoreDto;
import com.ebuozturk.productcategory.dto.store.UpdateStoreRequest;
import com.ebuozturk.productcategory.service.StoreService;
import java.util.List;
import javax.validation.Valid;
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
@RequestMapping({"v1/store"})
public class StoreController {
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }
    private final StoreService storeService;

    @GetMapping
    public ResponseEntity<List<StoreDto>> getAll() {
        return ResponseEntity.ok(this.storeService.getAll());
    }
    @GetMapping({"{id}"})
    public ResponseEntity<StoreDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(this.storeService.getById(id));
    }
    @PostMapping
    public ResponseEntity<StoreDto> createStore(@Valid @RequestBody CreateStoreRequest request) {
        return new ResponseEntity(this.storeService.createStore(request), HttpStatus.CREATED);
    }
    @PutMapping({"{id}"})
    public ResponseEntity<StoreDto> updateStore(@PathVariable String id, @Valid @RequestBody UpdateStoreRequest request) {
        return ResponseEntity.ok(this.storeService.updateStore(id, request));
    }
    @DeleteMapping({"{id}"})
    public ResponseEntity<Boolean> deleteStore(@PathVariable String id) {
        return ResponseEntity.ok(this.storeService.deleteById(id));
    }
}
