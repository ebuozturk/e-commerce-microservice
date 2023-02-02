package com.ebuozturk.basket.service;

import com.ebuozturk.basket.converter.BasketConverter;
import com.ebuozturk.basket.dto.basket.BasketDto;
import com.ebuozturk.basket.dto.product.Product;
import com.ebuozturk.basket.exception.BasketNotFoundException;
import com.ebuozturk.basket.exception.NoProductsInStockException;
import com.ebuozturk.basket.model.Basket;
import com.ebuozturk.basket.model.BasketProduct;
import com.ebuozturk.basket.repository.BasketRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashSet;
import java.util.List;

@Service
public class BasketService {
    private final BasketRepository repository;
    private final BasketConverter converter;
    private final BasketProductService basketProductService;
    private final WebClient.Builder webClientBuilder;

    public BasketService(BasketRepository repository, BasketConverter converter, BasketProductService basketProductService, WebClient.Builder webClientBuilder) {
        this.repository = repository;
        this.converter = converter;
        this.basketProductService = basketProductService;
        this.webClientBuilder = webClientBuilder;
    }

    public List<BasketDto> getAllBaskets(){
        return converter.convert(repository.findAll());
    }
    public BasketDto getBasketById(String id){
        return converter.convert(findById(id));
    }
    public BasketDto getBasketByUserId(String id){
        return converter.convert(findByUserId(id));
    }

    public BasketDto createBasket(String userId){
        record User(@JsonProperty("id") String id){

        }
        User user = webClientBuilder.build()
                .get()
                .uri("http://USER/v1/user/"+userId+"/id")
                .retrieve()
                .bodyToMono(User.class)
                .block();

        return converter.convert(repository.save(new Basket(user.id, new HashSet<BasketProduct>())));
    }
    public BasketDto addProductToBasket(String userId, String productId,int amountProduct){
        Basket basket = findByUserId(userId);

        Product product = webClientBuilder.build()
                .get()
                .uri("http://PRODUCT-CATEGORY/v1/product/"+productId)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
//                productService.findById(productId);

        if(basketProductService.isBasketProductExistByProductIdAndBasketId(productId,basket.getId())){

            BasketProduct basketProduct = basketProductService.findByProductIdAndBasketId(productId, basket.getId());
            basketProductService.increaseQuantityByProductId(amountProduct,basketProduct.getId());
            return getBasketById(basket.getId());
        }

        else{

            if(product.unitsInStock() > 0) {
                basketProductService.createBasketProduct(new BasketProduct(productId, basket,amountProduct));
                return converter.convert(basket);
            }
            else{
                throw new NoProductsInStockException("There are not enough products in stock!");
            }
        }

    }


    protected Basket findByUserId(String id) {
        return repository.findByUserId(id).orElseThrow(()-> new BasketNotFoundException("Basket couldn't be found by following user id: "+id));
    }

    protected Basket findById(String id){
        return repository.findById(id).orElseThrow(()-> new BasketNotFoundException("Basket couldn't be found by following id: "+id));
    }

}
