
package com.ebuozturk.productcategory.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Category
        extends BaseEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Category parentCategory;
    @OneToMany(mappedBy = "parentCategory", cascade = {CascadeType.ALL})
    private Set<Category> childrenCategories;
    private Integer position;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private Set<MainProduct> products;
    @ManyToMany(mappedBy = "categories", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Set<Feature> features; public Category() {} public Category(String name) {
        this.name = name;
    }


    public Category(String name, Integer position) {
        this.name = name;
        this.position = position;
    }
    public Category(String name, Category parentCategory) {
        this.name = name;
        this.parentCategory = parentCategory;
    }

    public Category(String id, String name, Category parentCategory, Integer position) {
        this.id = id;
        this.name = name;
        this.parentCategory = parentCategory;
        this.position = position;
    }

    public Category(LocalDateTime createdAt, LocalDateTime updatedAt, String name, Category parentCategory, Integer position) {
        super(createdAt, updatedAt);
        this.name = name;
        this.parentCategory = parentCategory;
        this.position = position;
    }

    public Category(String id, String name, Category parentCategory, Integer position, LocalDateTime updatedAt) {
        super(updatedAt);
        this.id = id;
        this.name = name;
        this.parentCategory = parentCategory;
        this.position = position;
    }

    public Category(String name, Integer position, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.name = name;
        this.position = position;
    }
    public Category(String name, Category parentCategory, Integer position, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.name = name;
        this.parentCategory = parentCategory;
        this.position = position;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Category getParentCategory() {
        return this.parentCategory;
    }

    public Set<Category> getChildrenCategories() {
        return this.childrenCategories;
    }

    public Integer getPosition() {
        return this.position;
    }

    public Set<MainProduct> getProducts() {
        return this.products;
    }

    public Set<Feature> getFeatures() {
        return this.features;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category)o;
        return (Objects.equals(this.id, category.id) && Objects.equals(this.name, category.name) && Objects.equals(this.position, category.position));
    }


    public int hashCode() {
        return Objects.hash(new Object[] { this.id, this.name, this.position });
    }


    public String toString() {
        return "Category{id='" + this.id + "', name='" + this.name + "', position=" + this.position + "}";
    }
}
