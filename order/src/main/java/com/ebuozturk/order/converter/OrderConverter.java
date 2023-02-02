package com.ebuozturk.order.converter;

import com.ebuozturk.order.dto.order.OrderDto;
import com.ebuozturk.order.dto.orderitem.OrderItemDto;
import com.ebuozturk.order.model.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderConverter {
    private final OrderAddressConverter addressConverter;

    public OrderConverter(OrderAddressConverter addressConverter) {
        this.addressConverter = addressConverter;
    }

    public OrderDto convert(Order order){
        return new OrderDto(
                order.getId(),
                order.getCreatedDate(),
                order.getTotalPrice(),
                order.getOrderItems()
                        .stream().map(orderItem -> {
                    return new OrderItemDto(
                            orderItem.getId(),
                            orderItem.getCreatedDate(),
                            orderItem.getQuantity(),
                            orderItem.getPrice(),
                            orderItem.getProductId(),
                            orderItem.getStatus().toString()
//                            productConverter.convert(orderItem.getProduct())
                    );
                }).collect(Collectors.toList()),
                addressConverter.convert(order.getOrderAddress()),
                addressConverter.convert(order.getBillAddress())
        );
    }
    public List<OrderDto> convert(List<Order> orderList){
        return orderList.stream().map(this::convert).collect(Collectors.toList());
    }
}
