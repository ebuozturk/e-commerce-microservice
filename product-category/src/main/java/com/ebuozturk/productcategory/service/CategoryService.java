package com.ebuozturk.productcategory.service;

import com.ebuozturk.productcategory.converter.CategoryConverter;
import com.ebuozturk.productcategory.dto.category.CategoryDto;
import com.ebuozturk.productcategory.dto.category.CreateCategoryRequest;
import com.ebuozturk.productcategory.dto.category.UpdateCategoryRequest;
import com.ebuozturk.productcategory.exception.CategoryNotFoundException;
import com.ebuozturk.productcategory.model.Category;
import com.ebuozturk.productcategory.model.Product;
import com.ebuozturk.productcategory.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repository;
    private final CategoryConverter converter;

    public CategoryService(CategoryRepository categoryRepository, CategoryConverter categoryConverter) {
        this.repository = categoryRepository;
        this.converter = categoryConverter;
    }

    public CategoryDto getCategoryById(String id){
        return converter.convert(findById(id));
    }
    public List<CategoryDto> getAllCategories(){
        return converter.convert(repository.findAll());
    }
    public CategoryDto createCategory(final CreateCategoryRequest request){
        return converter.convert(repository.save(new Category(request.getName(), new HashSet<Product>())));
    }
    public CategoryDto updateCategory(final String id,final UpdateCategoryRequest request){
        findById(id);
        return converter.convert(repository.save(new Category(id,request.getName(), new HashSet<Product>())));
    }
    public void deleteCategory(String id){
        if(doesCategoryExist(id))
            repository.deleteById(id);
        else
            throw new CategoryNotFoundException("Category isn't found by following id: "+id);
    }
    protected Boolean doesCategoryExist(String id){
        return repository.existsById(id);
    }
    protected Category findById(String id){
        return repository.findById(id).orElseThrow(()->new CategoryNotFoundException("Category isn't found by following id: "+id));
    }
}
