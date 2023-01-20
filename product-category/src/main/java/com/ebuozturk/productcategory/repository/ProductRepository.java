package com.ebuozturk.productcategory.repository;

import com.ebuozturk.productcategory.model.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, String> {
    @Query(value = "select * from product p\nwhere p.id = (select p.id from product p left outer join product_feature pf on p.id = pf.product_id\nwhere pf.feature_id in (?1) and p.product_id = ?2\ngroup by p.id\nhaving count(p.id) >1)", nativeQuery = true)
    Optional<Product> findProductByFeatures(List<String> paramList, String paramString);

    @Query(value = "select * from product p inner join main_product mp on mp.id = p.product_id where mp.category_id in (?1)", nativeQuery = true)
    List<Product> findAllByCategoryIdList(List<String> paramList);

    @Modifying
    @Query("update Product p set p.unitsInStock = :unitsInStock where p.id = :id")
    void updateUnitsInStock(@Param("id") String paramString, @Param("unitsInStock") Integer paramInteger);
}
