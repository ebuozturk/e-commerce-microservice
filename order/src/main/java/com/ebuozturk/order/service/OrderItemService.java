package com.ebuozturk.order.service;

import com.ebuozturk.order.converter.OrderItemConverter;
import com.ebuozturk.order.dto.order.OrderDto;
import com.ebuozturk.order.dto.orderitem.OrderItemDto;
import com.ebuozturk.order.exception.OrderNotFoundException;
import com.ebuozturk.order.model.Order;
import com.ebuozturk.order.model.OrderItem;
import com.ebuozturk.order.model.Status;
import com.ebuozturk.order.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService{

    private final OrderItemRepository repository;
    private final OrderItemConverter converter;

    public OrderItemService(OrderItemRepository repository, OrderItemConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    protected List<OrderItem> saveAllOrderItems(List<OrderItem> orderItemList) {
        return repository.saveAll(orderItemList);
    }
    public OrderItemDto updateStatus(String orderItemId, Status status){
        OrderItem orderItem = findById(orderItemId);
        OrderItem updateOrder =  new OrderItem(
                orderItem.getId(),
                orderItem.getQuantity(),
                orderItem.getPrice(),
                orderItem.getCreatedDate(),
                orderItem.getOrder(),
                orderItem.getProductId(),
                orderItem.getStatus()
                );
        return converter.convert(repository.save(updateOrder));
    }
    protected OrderItem findById(String id){
        return repository.findById(id).orElseThrow(()-> new OrderNotFoundException("The order item couldn't be found by following id: "+id));
    }
}
