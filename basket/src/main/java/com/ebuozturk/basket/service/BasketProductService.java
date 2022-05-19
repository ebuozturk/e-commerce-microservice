package com.ebuozturk.basket.service;

import com.ebuozturk.basket.converter.BasketProductConverter;
import com.ebuozturk.basket.dto.basketproduct.BasketProductDto;
import com.ebuozturk.basket.exception.BasketProductNotFoundException;
import com.ebuozturk.basket.model.BasketProduct;
import com.ebuozturk.basket.repository.BasketProductRepository;
import org.springframework.stereotype.Service;

@Service
public class BasketProductService {

    private final BasketProductRepository repository;
    private final BasketProductConverter converter;

    public BasketProductService(BasketProductRepository repository, BasketProductConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public BasketProductDto updateBasketProduct(BasketProduct product){
        return converter.convert(repository.save(product));
    }

    public BasketProductDto createBasketProduct(BasketProduct basketProduct) {
        return converter.convert(repository.save(basketProduct));
    }
    public void deleteBasketProduct(String id){
        findById(id);
        repository.deleteById(id);
    }
    public BasketProductDto increaseQuantityByProductId(int amount,String id){
        BasketProduct basketProduct = findById(id);
        basketProduct.increaseQuantity(amount);
        return converter.convert(repository.save(basketProduct));
    }

    public BasketProductDto decreaseQuantityByProductId(int amount,String id){
        BasketProduct basketProduct = findById(id);
        basketProduct.decreaseQuantity(amount);
        return converter.convert(repository.save(basketProduct));
    }
    protected BasketProduct findById(String id) {
        return repository.findById(id).orElseThrow(()-> new BasketProductNotFoundException("The product of basket couldn't be found by following id: "+id));
    }
    protected BasketProduct findByProductIdAndBasketId(String productId,String basketId){
        return repository.findByProductIdAndBasket_id(productId,basketId).orElseThrow(()-> new BasketProductNotFoundException("The product of basket couldn't be found" +
                " by following product id: "+productId+" and basket id: "+basketId));

    }
    protected boolean isBasketProductExist(String id){

        return repository.existsBasketProductById(id);
    }
    protected boolean isBasketProductExistByProductIdAndBasketId(String productId,String basketId){
        return repository.existsBasketProductByProductIdAndBasket_Id(productId,basketId);
    }
}
