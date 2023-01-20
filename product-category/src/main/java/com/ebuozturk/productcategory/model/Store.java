package com.ebuozturk.productcategory.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Store extends BaseEntity {
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
    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "stores"
    )

    private Set<Seller> sellers;
    @OneToMany(
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY,
            mappedBy = "store"
    )

    private Set<Product> products;

    public Store() {
        super();
    }
    public Store(String name, Set<Seller> sellers) {
        this();
        this.name = name;
        this.sellers = sellers;
    }
    public Store(String id, String name, Set<Seller> sellers) {
        this(name,sellers);
        this.id = id;
    }
    public Store(String id, String name, Set<Seller> sellers, Set<Product> products) {
        this(id,name,sellers);
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Seller> getSellers() {
        return sellers;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public String toString() {
        return "Store(id=" + this.id + ", name=" + this.name + ", sellers=" + this.sellers + ", products=" + this.products + ')';
    }

}
