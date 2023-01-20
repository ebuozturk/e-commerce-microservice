package com.ebuozturk.productcategory.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Brand extends BaseEntity {
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
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "brand"
    )
    private Set<MainProduct> products;

    public Brand() {
        super();

    }

    public Brand(String name) {
        this();
        this.name = name;
    }

    public Brand(String id, String name) {
        this(name);
        this.id = id;
    }

    public Brand(String id, String name, Set<MainProduct> products) {
        this(id,name);
        this.products = products;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Set<MainProduct> getProducts() {
        return this.products;
    }

    public String toString() {
        return "Brand(id=" + this.id + ", name=" + this.name + ", products=" + this.products + ')';
    }

}
