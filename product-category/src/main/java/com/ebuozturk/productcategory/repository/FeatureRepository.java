package com.ebuozturk.productcategory.repository;

import com.ebuozturk.productcategory.model.Feature;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<Feature, String> {
    List<Feature> findByFeatureType_id(String paramString);
}
