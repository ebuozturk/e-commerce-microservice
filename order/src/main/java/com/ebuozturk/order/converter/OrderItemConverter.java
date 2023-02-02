package com.ebuozturk.order.converter;

import com.ebuozturk.order.dto.orderitem.OrderItemDto;
import com.ebuozturk.order.model.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemConverter {

    public OrderItemDto convert(OrderItem orderItem){
        return new OrderItemDto(
                orderItem.getId(),
                orderItem.getCreatedDate(),
                orderItem.getQuantity(),
                orderItem.getPrice(),
                orderItem.getProductId(),
                orderItem.getStatus().toString()
        );
    }
}
