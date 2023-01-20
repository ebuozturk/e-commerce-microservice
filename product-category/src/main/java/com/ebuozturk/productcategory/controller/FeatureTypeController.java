
package com.ebuozturk.productcategory.controller;
import com.ebuozturk.productcategory.dto.featureType.CreateFeatureTypeRequest;
import com.ebuozturk.productcategory.dto.featureType.FeatureTypeDto;
import com.ebuozturk.productcategory.dto.featureType.UpdateFeatureTypeRequest;
import com.ebuozturk.productcategory.service.FeatureTypeService;
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
@RequestMapping({"v1/featureType"})
public class FeatureTypeController {
    public FeatureTypeController(FeatureTypeService featureTypeService) {
        this.featureTypeService = featureTypeService;
    }
    private final FeatureTypeService featureTypeService;
    @GetMapping
    public ResponseEntity<List<FeatureTypeDto>> getAllFeatureTypes() {
        return ResponseEntity.ok(this.featureTypeService.getAll());
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<FeatureTypeDto> getFeatureTypeById(@PathVariable String id) {
        return ResponseEntity.ok(this.featureTypeService.getById(id));
    }
    @GetMapping({"/category/{id}"})
    public ResponseEntity<List<FeatureTypeDto>> getFeatureTypesByCategoryId(@PathVariable String id) {
        return ResponseEntity.ok(this.featureTypeService.getAllByCategoryId(id));
    }

    @PostMapping
    public ResponseEntity<FeatureTypeDto> createFeatureType(@Valid @RequestBody CreateFeatureTypeRequest request) {
        return new ResponseEntity(this.featureTypeService.createFeatureType(request), HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<FeatureTypeDto> updateFeatureType(@PathVariable String id, @Valid @RequestBody UpdateFeatureTypeRequest request) {
        return ResponseEntity.ok(this.featureTypeService.updateFeatureType(id, request));
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<Boolean> deleteFeatureType(@PathVariable String id) {
        return ResponseEntity.ok(this.featureTypeService.deleteById(id));
    }
}
