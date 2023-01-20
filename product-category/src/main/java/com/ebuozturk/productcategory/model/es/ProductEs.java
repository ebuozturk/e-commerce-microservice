
package com.ebuozturk.productcategory.model.es;

import java.util.List;
import javax.persistence.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;



@Document(indexName = "ecommerce", createIndex = true)
public class ProductEs
{
    @Id
    private String id;
    @Field(type = FieldType.Text)
    private String productId;
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Text)
    private List<String> features;

    public ProductEs() {}

    public ProductEs(String productId, String name, List<String> features) {
        this.productId = productId;
        this.name = name;
        this.features = features;
    }

    public List<String> getFeatures() {
        return this.features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }


    public String toString() {
        return "EsProduct{id='" + this.id + "', productId='" + this.productId + "', name='" + this.name + "'}";
    }
}
