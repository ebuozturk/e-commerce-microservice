package com.ebuozturk.productcategory.dto.category;

import java.util.List;


public class CreateCategoryRequest {

    private  String name;
    private  String parentCategoryId;
    private  List<String> childCategories;

    public CreateCategoryRequest(){

    }
    public CreateCategoryRequest(String name, String parentCategoryId,  List<String> childCategories) {
        this.name = name;
        this.parentCategoryId = parentCategoryId;
        this.childCategories = childCategories;
    }

    public String getName() {
        return name;
    }

    public String getParentCategoryId() {
        return parentCategoryId;
    }

    public List<String> getChildCategories() {
        return childCategories;
    }

    public String toString() {
        return "CreateCategoryRequest(name=" + this.name + ", parentCategoryId=" + this.parentCategoryId + ", childCategories=" + this.childCategories + ')';
    }

    public int hashCode() {
        int result = this.name.hashCode();
        result = result * 31 + (this.parentCategoryId == null ? 0 : this.parentCategoryId.hashCode());
        result = result * 31 + (this.childCategories == null ? 0 : this.childCategories.hashCode());
        return result;
    }


}
