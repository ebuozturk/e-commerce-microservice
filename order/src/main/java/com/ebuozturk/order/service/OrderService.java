package com.ebuozturk.order.service;

import com.ebuozturk.order.client.*;
import com.ebuozturk.order.converter.OrderConverter;
import com.ebuozturk.order.dto.address.Address;
import com.ebuozturk.order.dto.basket.Basket;
import com.ebuozturk.order.dto.order.CreateOrderRequest;
import com.ebuozturk.order.dto.order.OrderDto;
import com.ebuozturk.order.exception.EmptyBasketException;
import com.ebuozturk.order.exception.OrderNotFoundException;
import com.ebuozturk.order.model.Order;
import com.ebuozturk.order.model.OrderAddress;
import com.ebuozturk.order.model.OrderItem;
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
    private final UserServiceClient userServiceClient;
    private final AddressServiceClient addressServiceClient;
    private final BasketServiceClient basketServiceClient;
    private final ProductServiceClient productServiceClient;
    private final BasketProductServiceClient basketProductServiceClient;

    public OrderService(OrderRepository repository, OrderConverter converter, WebClient.Builder webClientBuilder, UserServiceClient userServiceClient, AddressServiceClient addressServiceClient, BasketServiceClient basketServiceClient, ProductServiceClient productServiceClient, BasketProductServiceClient basketProductServiceClient) {
        this.repository = repository;
        this.converter = converter;
        this.webClientBuilder = webClientBuilder;
        this.userServiceClient = userServiceClient;
        this.addressServiceClient = addressServiceClient;
        this.basketServiceClient = basketServiceClient;
        this.productServiceClient = productServiceClient;
        this.basketProductServiceClient = basketProductServiceClient;
    }


    public OrderDto getOrderById(String id){
        return converter.convert(findById(id));
    }

    public List<OrderDto> getAllByUserId(String id) {
        return converter.convert(findByUserId(id));
    }

    public OrderDto placeOrder(CreateOrderRequest request){

        Boolean isUserExist = userServiceClient.doesUserExist(request.userId()).getBody();

        if(isUserExist){

            Basket basket = basketServiceClient.getBasketByUserId(request.userId()).getBody();

            if(basket.products().size()>0){

                Address orderAddress = addressServiceClient.getAddressById(request.deliveryAddressId()).getBody();

                Address billAddress = addressServiceClient.getAddressById(request.billAddressId()).getBody();

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
                        request.userId()
                );

                basket.products().forEach(basketProduct -> {

                    OrderItem orderItem = new OrderItem(
                            basketProduct.quantity(),
                            productServiceClient.getById(basketProduct.productId()).getBody().unitPrice(),
                            LocalDateTime.now(),
                            newOrder,
                            basketProduct.productId()
                    );

                    newOrder.getOrderItems().add(orderItem);

                    basketProductServiceClient.deleteBasketProduct(basketProduct.id());
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
