package com.ebuozturk.user.repository;

import com.ebuozturk.user.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart,String> {

    List<Cart> findByUser_id(String id);
}
