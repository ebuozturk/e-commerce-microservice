
package com.ebuozturk.productcategory.controller;
import com.ebuozturk.productcategory.dto.feature.CreateFeatureRequest;
import com.ebuozturk.productcategory.dto.feature.FeatureDto;
import com.ebuozturk.productcategory.dto.feature.UpdateFeatureRequest;
import com.ebuozturk.productcategory.service.FeatureService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin({"*"})
@RestController
@RequestMapping({"v1/feature"})
public class FeatureController {
    public FeatureController(FeatureService featureService) {
        this.featureService = featureService;
    }
    private final FeatureService featureService;
    @GetMapping
    public ResponseEntity<List<FeatureDto>> getAllFeatures() {
        return ResponseEntity.ok(this.featureService.getAll());
    }
    @GetMapping({"/{id}"})
    public ResponseEntity<FeatureDto> getFeatureById(@PathVariable String id) {
        return ResponseEntity.ok(this.featureService.getById(id));
    }
    @GetMapping({"/featureType/{featureTypeId}"})
    public ResponseEntity<List<FeatureDto>> getFeatureByFeatureTypeId(@PathVariable String featureTypeId) {
        return ResponseEntity.ok(this.featureService.getByFeatureTypeId(featureTypeId));
    }
    @PostMapping
    public ResponseEntity<FeatureDto> createFeature(@Valid @RequestBody CreateFeatureRequest request) {
        return new ResponseEntity(this.featureService.createFeature(request), HttpStatus.CREATED);
    }
    @PutMapping({"/{id}"})
    public ResponseEntity<FeatureDto> updateFeature(@PathVariable String id, @Valid @RequestBody UpdateFeatureRequest request) {
        return ResponseEntity.ok(this.featureService.updateFeature(id, request));
    }
    @DeleteMapping({"{id}"})
    public ResponseEntity<Boolean> deleteFeature(@PathVariable String id) {
        return ResponseEntity.ok(this.featureService.deleteFeature(id));
    }
}
