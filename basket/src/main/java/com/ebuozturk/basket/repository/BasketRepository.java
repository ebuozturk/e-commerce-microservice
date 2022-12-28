package com.ebuozturk.basket.repository;

import com.ebuozturk.basket.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket,String> {
    Optional<Basket> findByUserId(String id);

}
