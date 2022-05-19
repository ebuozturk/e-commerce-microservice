package com.ebuozturk.order.controller;

import com.ebuozturk.order.dto.order.OrderDto;
import com.ebuozturk.order.model.Status;
import com.ebuozturk.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("v1/order")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDto>> getAllOrdersByUserId(@PathVariable String userId){
        return ResponseEntity.ok(service.getAllByUserId(userId));
    }
    @GetMapping("{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable String id){
        return ResponseEntity.ok(service.getOrderById(id));
    }
    @PostMapping
    public ResponseEntity<OrderDto> placeOrder(@RequestParam("userId") String userId,
                                               @RequestParam("orderAddressId") String orderAddressId,
                                               @RequestParam("billAddressId") String billAddressId
                                               ){
        return ResponseEntity.ok(service.placeOrder(userId,orderAddressId,billAddressId));
    }

    @PutMapping("{orderId}")
    public ResponseEntity<OrderDto> updateOrderStatus(@PathVariable("orderId") String orderId, @RequestParam("status") String status){
        return new ResponseEntity<>(service.updateStatus(orderId, Status.valueOf(status.toUpperCase())), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable String id){
        service.deleteOrderById(id);
        return ResponseEntity.ok().build();
    }
}
