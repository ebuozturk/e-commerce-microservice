
package com.ebuozturk.productcategory.model;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class FeatureType
        extends BaseEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    public FeatureType() {
        super();
    }

    public FeatureType(String id, String name) {
        this();
        this.id = id;
        this.name = name;
    }

    public FeatureType(String name, Category category, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.name = name;
        this.category = category;
    }

    public FeatureType(String id, String name, Category category, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Category getCategory() {
        return this.category;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeatureType that = (FeatureType) o;
        return (Objects.equals(this.id, that.id) && Objects.equals(this.name, that.name));
    }


    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.name});
    }


    public String toString() {
        return "FeatureType{id='" + this.id + "', name='" + this.name + "'}";
    }
}