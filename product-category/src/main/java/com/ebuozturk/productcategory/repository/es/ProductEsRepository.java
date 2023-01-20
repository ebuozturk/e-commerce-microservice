package com.ebuozturk.productcategory.repository.es;

import com.ebuozturk.productcategory.model.es.ProductEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductEsRepository extends ElasticsearchRepository<ProductEs, String> {
}
