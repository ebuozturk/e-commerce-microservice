package com.ebuozturk.productcategory.repository;

import com.ebuozturk.productcategory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {

    List<Product> findByCategory_id(String id);

    @Query(value = "Select * from product where ts_tsvector('name') @@ ts_tsquery(:name)",nativeQuery = true)
    List<Product> search(@Param("name") String name);
}
