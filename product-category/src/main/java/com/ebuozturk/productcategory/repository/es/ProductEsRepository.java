package com.ebuozturk.productcategory.repository.es;

import com.ebuozturk.productcategory.model.es.ProductEs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductEsRepository extends ElasticsearchRepository<ProductEs,String> {

    @Query("{\"match\": {\"name\": {\"query\": \"?0\", \"fuzziness\": \"AUTO\"}}}")
    Page<ProductEs> findByNameFuzzy(String name, Pageable pageable);
}
