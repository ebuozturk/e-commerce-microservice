package com.ebuozturk.order.service;

import com.ebuozturk.order.converter.OrderConverter;
import com.ebuozturk.order.dto.address.Address;
import com.ebuozturk.order.dto.basket.Basket;
import com.ebuozturk.order.dto.order.OrderDto;
import com.ebuozturk.order.dto.product.Product;
import com.ebuozturk.order.exception.EmptyBasketException;
import com.ebuozturk.order.exception.OrderNotFoundException;
import com.ebuozturk.order.model.Order;
import com.ebuozturk.order.model.OrderAddress;
import com.ebuozturk.order.model.OrderItem;
import com.ebuozturk.order.model.Status;
import com.ebuozturk.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final OrderConverter converter;
    private final WebClient.Builder webClientBuilder;

    public OrderService(OrderRepository repository, OrderConverter converter, WebClient.Builder webClientBuilder) {
        this.repository = repository;
        this.converter = converter;
        this.webClientBuilder = webClientBuilder;
    }


    public OrderDto getOrderById(String id){
        return converter.convert(findById(id));
    }

    public List<OrderDto> getAllByUserId(String id) {
        return converter.convert(findByUserId(id));
    }

    public OrderDto placeOrder(String userId,String orderAddressId,String billAddressId){

        Boolean isUserExist = webClientBuilder.build()
                .get()
                .uri("http://USER/v1/user/isExist/"+userId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if(isUserExist){
            Address orderAddress = webClientBuilder.build()
                    .get()
                    .uri("http://USER/v1/address/"+orderAddressId)
                    .retrieve()
                    .bodyToMono(Address.class)
                    .block();

            Address billAddress = webClientBuilder.build()
                    .get()
                    .uri("http://USER/v1/address/"+billAddressId)
                    .retrieve()
                    .bodyToMono(Address.class)
                    .block();

            Basket basket = webClientBuilder.build()
                    .get()
                    .uri("http://BASKET/v1/basket/"+userId)
                    .retrieve()
                    .bodyToMono(Basket.class)
                    .block();

            if(basket.products().size()>0){
                Order newOrder = new Order(
                        LocalDateTime.now(),
                        basket.totalPrice(),
                        new OrderAddress(orderAddress.getAddressName(),
                                orderAddress.getPhoneNumber(),
                                orderAddress.getFirstName(),
                                orderAddress.getLastName(),
                                orderAddress.getCountry(),
                                orderAddress.getCity(),
                                orderAddress.getFullAddress()
                        ),
                        new OrderAddress(billAddress.getAddressName(),
                                billAddress.getPhoneNumber(),
                                billAddress.getFirstName(),
                                billAddress.getLastName(),
                                billAddress.getCountry(),
                                billAddress.getCity(),
                                billAddress.getFullAddress()
                        ),
                        userId
                );

                basket.products().forEach(basketProduct -> {

                    OrderItem orderItem = new OrderItem(
                            basketProduct.quantity(),
                            webClientBuilder.build()
                                            .get()
                                            .uri("http://PRODUCT-CATEGORY/v1/product/"+basketProduct.productId())
                                                    .retrieve()
                                                            .bodyToMono(Product.class)
                                                                    .block().unitPrice(),
                            LocalDateTime.now(),
                            newOrder,
                            basketProduct.productId()
                    );

                    newOrder.getOrderItems().add(orderItem);

                    Void result  = webClientBuilder.build()
                                    .delete()
                                            .uri("http://BASKET/v1/basketProduct/"+basketProduct.id())
                            .retrieve().bodyToMono(Void.class).block();
                });
                return converter.convert(repository.save(newOrder));
            }
            else{
                throw new EmptyBasketException("There is no product in basket!");
            }
        }
        else{
            throw new IllegalStateException("User not found!");
        }
    }

    public OrderDto updateStatus(String orderId, Status status){
        Order order = findById(orderId);
        Order updateOrder =  new Order(order.getId(),
                order.getCreatedDate(),
                order.getTotalPrice(),
                order.getUserId(),
                order.getOrderAddress(),
                order.getBillAddress(),
                order.getOrderItems(),
                status);
        return converter.convert(repository.save(updateOrder));
    }

    public void deleteOrderById(String id) {
        Order  order = findById(id);
        repository.delete(order);
    }
    protected List<Order> findByUserId(String userId){
        return repository.findByUserId(userId);
    }

    protected Order findById(String id){
        return repository.findById(id).orElseThrow(()-> new OrderNotFoundException("The order couldn't be found by following id: "+id));
    }

}
