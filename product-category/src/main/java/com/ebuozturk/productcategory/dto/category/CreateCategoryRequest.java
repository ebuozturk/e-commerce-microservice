package com.ebuozturk.productcategory.dto.category;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class CreateCategoryRequest {

    private  String name;
    private  String parentCategoryId;
    private  List<String> childCategories;

    public CreateCategoryRequest(String name, String parentCategoryId,  List<String> childCategories) {
        super();
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
