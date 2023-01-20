package com.ebuozturk.productcategory.repository;

import com.ebuozturk.productcategory.model.FeatureType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FeatureTypeRepository extends JpaRepository<FeatureType, String> {
    @Query("SELECT ft FROM FeatureType ft LEFT JOIN FETCH ft.category WHERE ft.id = ?1")
    Optional<FeatureType> findFeatureTypeById(String paramString);

    List<FeatureType> findFeatureTypeByCategory_id(String paramString);
}
