package com.ebuozturk.productcategory.dto.mainproduct;

import com.ebuozturk.productcategory.dto.brand.BrandDto;
import com.ebuozturk.productcategory.dto.category.CategoryDto;
import com.ebuozturk.productcategory.model.WarrantyType;

public class MainProductDto {

    private String id;

    private String name;

    private String description;

    private BrandDto brand;

    private WarrantyType warrantType;

    private CategoryDto category;

    public MainProductDto(String id,String name,String description, BrandDto brand, WarrantyType warrantType, CategoryDto category) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.warrantType = warrantType;
        this.category = category;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public BrandDto getBrand() {
        return this.brand;
    }

    public WarrantyType getWarrantType() {
        return this.warrantType;
    }

    public CategoryDto getCategory() {
        return this.category;
    }

    public String toString() {
        return "MainProductDto(id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", brand=" + this.brand + ", warrantType=" + this.warrantType + ", category=" + this.category + ')';
    }

    public int hashCode() {
        int result = this.id == null ? 0 : this.id.hashCode();
        result = result * 31 + this.name.hashCode();
        result = result * 31 + this.description.hashCode();
        result = result * 31 + this.brand.hashCode();
        result = result * 31 + this.warrantType.hashCode();
        result = result * 31 + this.category.hashCode();
        return result;
    }

}
