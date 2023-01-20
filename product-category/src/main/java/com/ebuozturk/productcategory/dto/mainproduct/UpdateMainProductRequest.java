package com.ebuozturk.productcategory.dto.mainproduct;


public class UpdateMainProductRequest {

    private String name;

    private String description;

    private String brandId;

    private String categoryId;

    private String warrantyType;

    public UpdateMainProductRequest(String name, String description,String brandId, String categoryId, String warrantyType) {
        super();
        this.name = name;
        this.description = description;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.warrantyType = warrantyType;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getBrandId() {
        return this.brandId;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public String getWarrantyType() {
        return this.warrantyType;
    }

    public String toString() {
        return "UpdateMainProductRequest(name=" + this.name + ", description=" + this.description + ", brandId=" + this.brandId + ", categoryId=" + this.categoryId + ", warrantyType=" + this.warrantyType + ')';
    }

    public int hashCode() {
        int result = this.name.hashCode();
        result = result * 31 + this.description.hashCode();
        result = result * 31 + this.brandId.hashCode();
        result = result * 31 + this.categoryId.hashCode();
        result = result * 31 + this.warrantyType.hashCode();
        return result;
    }

}
