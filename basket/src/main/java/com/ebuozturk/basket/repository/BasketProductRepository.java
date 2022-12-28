package com.ebuozturk.basket.repository;


import com.ebuozturk.basket.model.BasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BasketProductRepository extends JpaRepository<BasketProduct,String> {
   Optional<BasketProduct> findByProductIdAndBasket_id(String productId, String basketId);
   Boolean existsBasketProductById(String id);
   Boolean existsBasketProductByProductIdAndBasket_Id(String productId,String basketId);
}
