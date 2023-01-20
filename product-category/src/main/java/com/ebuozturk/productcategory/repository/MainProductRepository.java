package com.ebuozturk.productcategory.repository;

import com.ebuozturk.productcategory.model.MainProduct;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MainProductRepository extends JpaRepository<MainProduct, String> {
    List<MainProduct> findByCategory_id(String paramString);

    @Query(value = "Select * from product where ts_tsvector('name') @@ ts_tsquery(:name)", nativeQuery = true)
    List<MainProduct> search(@Param("name") String paramString);

    List<MainProduct> getAllByCategory_id(String paramString);
}