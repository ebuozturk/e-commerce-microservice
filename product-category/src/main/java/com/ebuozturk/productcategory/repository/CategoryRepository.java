package com.ebuozturk.productcategory.repository;

import com.ebuozturk.productcategory.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,String> {

}
