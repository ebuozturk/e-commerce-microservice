package com.ebuozturk.order.repository;

import com.ebuozturk.order.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,String> {
}
