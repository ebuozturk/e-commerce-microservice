package com.ebuozturk.images.repository;

import com.ebuozturk.images.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductImageRepository extends JpaRepository<ProductImage,String> {

    List<ProductImage> findAllByProductIdOrderByPosition(String productId);
    Integer countByProductId(String productId);
    Optional<ProductImage> findByKey(String key);
    Optional<ProductImage> findFirstByProductIdOrderByPosition(String productId);
    Optional<ProductImage> findByProductId(String productId);
}
