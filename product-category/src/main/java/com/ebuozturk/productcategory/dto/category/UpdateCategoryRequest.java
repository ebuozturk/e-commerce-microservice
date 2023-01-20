package com.ebuozturk.productcategory.dto.category;

public class UpdateCategoryRequest {

    private String name;
    private String parentCategoryId;
    private Integer position;

    public UpdateCategoryRequest(String name, String parentCategoryId, Integer position) {
        super();
        this.name = name;
        this.parentCategoryId = parentCategoryId;
        this.position = position;
    }

    public String getName() {
        return this.name;
    }

    public String getParentCategoryId() {
        return this.parentCategoryId;
    }

    public Integer getPosition() {
        return this.position;
    }

    public String toString() {
        return "UpdateCategoryRequest(name=" + this.name + ", parentCategoryId=" + this.parentCategoryId + ", position=" + this.position + ')';
    }

    public int hashCode() {
        int result = this.name.hashCode();
        result = result * 31 + (this.parentCategoryId == null ? 0 : this.parentCategoryId.hashCode());
        result = result * 31 + this.position.hashCode();
        return result;
    }

}
