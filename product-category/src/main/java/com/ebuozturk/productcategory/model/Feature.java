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
public class Feature extends BaseEntity {
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
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "feature_type_id",
            referencedColumnName = "id"
    )

    private FeatureType featureType;
    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "features"
    )

    private Set<Product> products;
    @ManyToMany(
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "feature_category",
            joinColumns = {@JoinColumn(
                    name = "feature_id",
                    referencedColumnName = "id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "category_id",
                    referencedColumnName = "id"
            )}
    )

    private Set<Category> categories;

    public Feature() {
        super();
    }

    public Feature(String name, FeatureType featureType, Set<Category> categories) {
        this();
        this.name = name;
        this.featureType = featureType;
        this.categories = categories;
    }
    public Feature(String id, String name, FeatureType featureType, Set<Category> categories) {
        this(name,featureType,categories);
        this.id = id;
    }

    public Feature(String name, FeatureType featureType, Set<Product> products, Set<Category> categories) {
        this(name,featureType,categories);
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public FeatureType getFeatureType() {
        return featureType;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public int hashCode() {
        return this.getClass().hashCode();
    }

    public String toString() {
        return "Feature(id=" + this.id + ", name='" + this.name + "', featureType=" + this.featureType;
    }


}
