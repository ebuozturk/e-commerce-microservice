package com.ebuozturk.productcategory.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class MainProduct extends BaseEntity {
    @Id
    @GeneratedValue(
            generator = "UUID"
    )
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )

    private String id;

    private String name;

    private String description;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "id"
    )

    private Category category;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "mainProduct"
    )

    private List<Product> products;
    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "brand_id",
            referencedColumnName = "id"
    )
    private Brand brand;

    @Enumerated(EnumType.ORDINAL)
    private WarrantyType warrantyType;

    public MainProduct() {
        super();
    }

    public MainProduct(String name, String description, Category category, Brand brand, WarrantyType warrantyType) {
        this();
        this.name = name;
        this.description = description;
        this.category = category;
        this.brand = brand;
        this.warrantyType = warrantyType;
    }
    public MainProduct(String name, String description, Category category, List<Product> products, Brand brand, WarrantyType warrantyType) {
        this(name,description,category,brand,warrantyType);
        this.products = products;
    }

    public MainProduct(String id, String name, String description, Category category, Brand brand, WarrantyType warrantyType) {
        this(name,description,category,brand,warrantyType);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Brand getBrand() {
        return brand;
    }

    public WarrantyType getWarrantyType() {
        return warrantyType;
    }

    public String toString() {
        return "MainProduct(id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", category=" + this.category + ", products=" + this.products + ", brand=" + this.brand + ", warrantyType=" + this.warrantyType + ')';
    }

}
