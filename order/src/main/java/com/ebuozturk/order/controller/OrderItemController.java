package com.ebuozturk.order.controller;

import com.ebuozturk.order.dto.order.OrderDto;
import com.ebuozturk.order.dto.orderitem.OrderItemDto;
import com.ebuozturk.order.model.Status;
import com.ebuozturk.order.service.OrderItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("/v1/orderItem")
public class OrderItemController {

    private final OrderItemService service;

    public OrderItemController(OrderItemService service) {
        this.service = service;
    }

    @PutMapping("{orderItemId}")
    public ResponseEntity<OrderItemDto> updateOrderStatus(@PathVariable("orderItemId") String orderItemId, @RequestParam("status") String status){
        return new ResponseEntity<>(service.updateStatus(orderItemId, Status.valueOf(status.toUpperCase())), HttpStatus.ACCEPTED);
    }
}
