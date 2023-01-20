package com.ebuozturk.productcategory.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(
            generator = "UUID"
    )
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )

    private String id;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "id"
    )

    private MainProduct mainProduct;

    private Integer unitsInStock;

    private double unitPrice;

    private Integer quantityPerUnit;
    @ManyToMany(
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "product_feature",
            joinColumns = {@JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "feature_id",
                    referencedColumnName = "id"
            )}
    )
    private Set<Feature> features;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "store_id",
            referencedColumnName = "id"
    )
    private Store store;

    public Product() {
        super();
    }

    public Product(MainProduct mainProduct, Integer unitsInStock, double unitPrice, Integer quantityPerUnit, Set<Feature> features, Store store) {
        this();
        this.mainProduct = mainProduct;
        this.unitsInStock = unitsInStock;
        this.unitPrice = unitPrice;
        this.quantityPerUnit = quantityPerUnit;
        this.features = features;
        this.store = store;
    }
    public Product(String id, MainProduct mainProduct, Integer unitsInStock, double unitPrice, Integer quantityPerUnit, Set<Feature> features, Store store) {
        this(mainProduct,unitsInStock,unitPrice,quantityPerUnit,features,store);
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public MainProduct getMainProduct() {
        return mainProduct;
    }

    public Integer getUnitsInStock() {
        return unitsInStock;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public Integer getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public Set<Feature> getFeatures() {
        return features;
    }

    public Store getStore() {
        return store;
    }

    public String toString() {
        return "Product(id=" + this.id + ", mainProduct=" + this.mainProduct + ", unitsInStock=" + this.unitsInStock + ", unitPrice=" + this.unitPrice + ", quantityPerUnit=" + this.quantityPerUnit + ", features=" + this.features + ", store=" + this.store + ')';
    }

}
