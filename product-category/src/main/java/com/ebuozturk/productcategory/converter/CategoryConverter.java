
package com.ebuozturk.productcategory.converter;

import com.ebuozturk.productcategory.dto.category.CategoryDto;
import com.ebuozturk.productcategory.dto.category.CategoryWithChildDto;
import com.ebuozturk.productcategory.dto.category.CategoryWithParentDto;
import com.ebuozturk.productcategory.model.Category;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;


@Component
public class CategoryConverter
{
    public CategoryDto convertToCategoryDto(Category category) {
        return new CategoryDto(category.getId(), category.getName(), category.getPosition());
    }
    public Set<CategoryDto> convertToCategoryDto(Set<Category> categories) {
        return (Set<CategoryDto>)categories.stream().map(this::convertToCategoryDto).collect(Collectors.toSet());
    }


    public List<CategoryDto> convertToCategoryDto(List<Category> categories) {
        return (List<CategoryDto>)categories.stream().map(this::convertToCategoryDto).collect(Collectors.toList());
    }


    public CategoryDto convertToCategoryWithChildDto(Category category) {
        return (CategoryDto)new CategoryWithChildDto(category.getId(), category.getName(), category.getPosition(), convertToCategoryWithChildDto(category.getChildrenCategories()));
    }

    public List<CategoryDto> convertToCategoryWithChildDto(List<Category> categories) {
        return (List<CategoryDto>)categories.stream().map(this::convertToCategoryWithChildDto).collect(Collectors.toList());
    }
    public List<CategoryDto> convertToCategoryWithChildDto(Set<Category> categories) {
        return (List<CategoryDto>)categories.stream().map(this::convertToCategoryWithChildDto).collect(Collectors.toList());
    }



    public CategoryDto convertToCategoryWithParentDto(Category category) {
        return Objects.isNull(category.getParentCategory()) ?
                new CategoryDto(category.getId(), category.getName(), category.getPosition()) :
                new CategoryWithParentDto(category.getId(), category.getName(), category.getPosition(), convertToCategoryDto(category.getParentCategory()));
    }

    public Set<CategoryDto> convertToCategoryWithParentDto(Set<Category> categories) {
        return (Set<CategoryDto>)categories.stream().map(this::convertToCategoryWithParentDto).collect(Collectors.toSet());
    }
    public List<CategoryDto> convertToCategoryWithParentDto(List<Category> categories) {
        return (List<CategoryDto>)categories.stream().map(this::convertToCategoryWithParentDto).collect(Collectors.toList());
    }
}
