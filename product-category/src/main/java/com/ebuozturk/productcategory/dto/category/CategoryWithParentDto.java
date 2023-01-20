package com.ebuozturk.productcategory.dto.category;


public class CategoryWithParentDto
        extends CategoryDto
{
    private CategoryDto parentCategoryDto;

    public CategoryWithParentDto(String id, String name, Integer position, CategoryDto parentCategoryDto) {
        super(id, name, position);
        this.parentCategoryDto = parentCategoryDto;
    }

    public CategoryDto getParentCategoryDto() {
        return this.parentCategoryDto;
    }
}
