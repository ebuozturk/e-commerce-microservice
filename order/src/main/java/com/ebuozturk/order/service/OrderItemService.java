package com.ebuozturk.order.service;

import com.ebuozturk.order.model.OrderItem;
import com.ebuozturk.order.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record OrderItemService(OrderItemRepository repository) {

    public void saveOrderItem(OrderItem orderItem) {
        repository.save(orderItem);
    }

    protected List<OrderItem> saveAllOrderItems(List<OrderItem> orderItemList) {
        return repository.saveAll(orderItemList);
    }
}
