package com.ebuozturk.productcategory.repository;

import com.ebuozturk.productcategory.model.Category;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, String> {
    Integer countByParentCategory_Id(String var1);

    Optional<Category> findByPosition(Integer var1);

    @Query("Select c from Category c WHERE (c.parentCategory.id IS NULL)")
    List<Category> findAllMainCategories();

    List<Category> findAllByParentCategory_id(String var1);

    @Query(
            value = "WITH RECURSIVE rec (id) as (  SELECT c.* from category c where c.id= ?1 UNION ALL SELECT c2.* from rec, category c2 where c2.parent_id = rec.id) SELECT * FROM rec",
            nativeQuery = true
    )
    List<Category> findAllCategoriesByCategoryId(String var1);

    Boolean existsCategoryByNameAndParentCategory_Id(String var1, String var2);
}