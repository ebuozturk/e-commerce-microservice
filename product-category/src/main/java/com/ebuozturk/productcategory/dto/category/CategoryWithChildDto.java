package com.ebuozturk.productcategory.dto.category;

import java.util.List;

public class CategoryWithChildDto
        extends CategoryDto
{
    private List<CategoryDto> childCategories;

    public CategoryWithChildDto(String id, String name, Integer position, List<CategoryDto> childCategories) {
        super(id, name, position);
        this.childCategories = childCategories;
    }

    public List<CategoryDto> getChildCategories() {
        return this.childCategories;
    }
}