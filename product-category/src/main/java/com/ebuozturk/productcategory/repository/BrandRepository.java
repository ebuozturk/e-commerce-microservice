package com.ebuozturk.productcategory.repository;

import com.ebuozturk.productcategory.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, String> {
}
