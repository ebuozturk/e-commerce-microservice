package com.ebuozturk.productcategory.converter;

import com.ebuozturk.productcategory.dto.category.CategoryDto;
import com.ebuozturk.productcategory.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryConverter {

    public CategoryDto convert(Category category){

        return new CategoryDto(category.getId(), category.getName());
    }
    public List<CategoryDto> convert(List<Category> categories){
        return categories.stream().map(this::convert).collect(Collectors.toList());
    }
}
