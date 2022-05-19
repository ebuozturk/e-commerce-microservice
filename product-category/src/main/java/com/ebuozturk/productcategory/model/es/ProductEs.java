package com.ebuozturk.productcategory.model.es;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;

@Document(indexName = "product")
public class ProductEs {

    @Id
    private String id;
    @Field(type = FieldType.Text)
    private String productId;
    @Field(type = FieldType.Text)
    private String name;

    public ProductEs() {
    }

    public ProductEs(String productId, String name) {
        this.productId = productId;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EsProduct{" +
                "id='" + id + '\'' +
                ", productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
