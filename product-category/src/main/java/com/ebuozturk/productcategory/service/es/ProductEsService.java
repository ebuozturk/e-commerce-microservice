
package com.ebuozturk.productcategory.service.es;

import com.ebuozturk.productcategory.model.es.ProductEs;
import com.ebuozturk.productcategory.repository.es.ProductEsRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.MultiMatchQueryBuilder.Type;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class ProductEsService {
    private final ProductEsRepository productEsRepository;
    private final ReactiveElasticsearchOperations elasticsearchOperations;

    public ProductEsService(ProductEsRepository productEsRepository, ReactiveElasticsearchOperations elasticsearchOperations) {
        this.productEsRepository = productEsRepository;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public List<ProductEs> getAllProducts() {
        return (List)StreamSupport.stream(this.productEsRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public List<ProductEs> getByQuery(String queryString) {
        Query searchQuery = (new NativeSearchQueryBuilder()).withQuery(QueryBuilders.multiMatchQuery(queryString, new String[0]).field("name").field("features").operator(Operator.AND).type(Type.CROSS_FIELDS)).build();
        return (List)this.elasticsearchOperations.search(searchQuery, ProductEs.class).map((searchHit) -> {
            return (ProductEs)searchHit.getContent();
        }).collect(Collectors.toList()).block();
    }

    public void createProductEs(ProductEs productEs) {
        this.productEsRepository.save(productEs);
    }
}
