package com.ebuozturk.productcategory.service.es;

import com.ebuozturk.productcategory.model.es.ProductEs;
import com.ebuozturk.productcategory.repository.es.ProductEsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductEsService {

    private final ProductEsRepository productEsRepository;

    public ProductEsService(ProductEsRepository productEsRepository) {
        this.productEsRepository = productEsRepository;
    }

    public List<ProductEs> getAllProducts(){
        return StreamSupport.stream(productEsRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }
    public Page<ProductEs> getByNameFuzzy(String name, Pageable pageable){

        return productEsRepository.findByNameFuzzy(name, pageable);
    }

    public void createProductEs(ProductEs productEs) {
            productEsRepository.save(productEs);
    }
}
