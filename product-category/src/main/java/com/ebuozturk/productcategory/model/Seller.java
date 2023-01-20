package com.ebuozturk.productcategory.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Seller extends BaseEntity {
    @Id
    @GeneratedValue(
            generator = "UUID"
    )
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )

    private String id;

    private String firstName;

    private String lastName;

    private String email;
    @ManyToMany(
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "store_seller",
            joinColumns = {@JoinColumn(
                    name = "seller_id",
                    referencedColumnName = "id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "store_id",
                    referencedColumnName = "id"
            )}
    )

    private Set<Store> stores;

    public Seller() {
        super();
    }

    public Seller(String firstName, String lastName, String email) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Seller(String id, String firstName, String lastName, String email) {
        this(firstName,lastName,email);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Set<Store> getStores() {
        return stores;
    }
}
