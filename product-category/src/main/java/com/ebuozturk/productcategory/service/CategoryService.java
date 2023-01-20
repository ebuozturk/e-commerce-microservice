
package com.ebuozturk.productcategory.service;

import com.ebuozturk.productcategory.converter.CategoryConverter;
import com.ebuozturk.productcategory.dto.category.CategoryDto;
import com.ebuozturk.productcategory.dto.category.CreateCategoryRequest;
import com.ebuozturk.productcategory.dto.category.UpdateCategoryRequest;
import com.ebuozturk.productcategory.exception.CategoryNotFoundException;
import com.ebuozturk.productcategory.exception.NotFoundException;
import com.ebuozturk.productcategory.model.Category;
import com.ebuozturk.productcategory.repository.CategoryRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService
{
    private final CategoryRepository categoryRepository;
    private final CategoryConverter converter;

    public CategoryService(CategoryRepository categoryRepository, CategoryConverter categoryConverter) {
        this.categoryRepository = categoryRepository;
        this.converter = categoryConverter;
    }

    public CategoryDto getCategoryById(String id) {
        return this.converter.convertToCategoryWithParentDto(findById(id));
    }

    public List<CategoryDto> getAllCategories() {
        return this.converter.convertToCategoryDto(this.categoryRepository.findAll());
    }
    public List<CategoryDto> getAllCategoriesWithParent() {
        return this.converter.convertToCategoryWithParentDto(this.categoryRepository.findAll());
    }
    public List<CategoryDto> getAllCategoriesWithChild() {
        return this.converter.convertToCategoryWithChildDto((List<Category>)this.categoryRepository.findAll().stream().filter(category -> Objects.isNull(category.getParentCategory())).collect(Collectors.toList()));
    }
    public List<CategoryDto> getAllMainCategories() {
        return this.converter.convertToCategoryDto(this.categoryRepository.findAllMainCategories());
    }
    public List<CategoryDto> getAllChildCategoriesByParentId(String parentId) {
        return this.converter.convertToCategoryDto(this.categoryRepository.findAllByParentCategory_id(parentId));
    }
    public List<CategoryDto> getAllCategoriesByCategoryId(String id) {
        return this.converter.convertToCategoryDto(this.categoryRepository.findAllCategoriesByCategoryId(id));
    }

    public CategoryDto createCategory(CreateCategoryRequest request) {
        if (!isExistByName(request.getName(), request.getParentCategoryId()).booleanValue()) {
            boolean isParentExist = !Objects.equals(request.getParentCategoryId(), "");
            Category parentCategory = isParentExist ? findById(request.getParentCategoryId()) : new Category();
            Integer position = Integer.valueOf(isParentExist ? (this.categoryRepository.countByParentCategory_Id(parentCategory.getId()).intValue() + 1) : 1);
            return isParentExist ?
                    this.converter.convertToCategoryDto((Category)this.categoryRepository.save(new Category(request.getName(), parentCategory, position, LocalDateTime.now(), LocalDateTime.now()))) :
                    this.converter.convertToCategoryDto((Category)this.categoryRepository.save(new Category(request.getName(), position, LocalDateTime.now(), LocalDateTime.now())));
        }
        throw new CategoryNotFoundException(String.format("The category with %s name is already exist!", new Object[] { request.getName() }));
    }


    @Transactional(rollbackFor = {Exception.class})
    public CategoryDto updateCategory(String id, UpdateCategoryRequest request) {
        Category category = findById(id);
        if (!Objects.equals(category.getPosition(), request.getPosition())) {
            Category categoryAtRequestedPosition = findByPosition(request.getPosition());




            Category updateCategoryAtRequestedPosition = new Category(categoryAtRequestedPosition.getId(), categoryAtRequestedPosition.getName(), categoryAtRequestedPosition.getParentCategory(), categoryAtRequestedPosition.getPosition(), LocalDateTime.now());

            this.categoryRepository.save(updateCategoryAtRequestedPosition);
        }
        Category parentCategory = new Category();

        if (!Objects.isNull(category.getParentCategory()))
        {

            parentCategory = Objects.equals(category.getParentCategory().getId(), request.getParentCategoryId()) ? category.getParentCategory() : findById(request.getParentCategoryId());
        }

        return this.converter.convertToCategoryDto((Category)this.categoryRepository.save(new Category(id, request.getName(), parentCategory, request.getPosition(), LocalDateTime.now())));
    }
    public void deleteCategory(String id) {
        if (doesCategoryExist(id).booleanValue()) {
            this.categoryRepository.deleteById(id);
        } else {
            throw new CategoryNotFoundException("Category isn't found by following id: " + id);
        }
    } protected Boolean doesCategoryExist(String id) {
    return Boolean.valueOf(this.categoryRepository.existsById(id));
}

    protected Category findById(String id) {
        return (Category)this.categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category isn't found by following id: " + id));
    }

    protected List<Category> findCategoryListByIdList(List<String> idList) {
        return (List<Category>)idList.stream().map(this::findById).collect(Collectors.toList());
    }
    protected Set<Category> findCategorySetByIdList(List<String> idList) {
        return (Set<Category>)idList.stream().map(this::findById).collect(Collectors.toSet());
    }
    protected Boolean isExistByName(String name, String parentCategoryId) {
        return this.categoryRepository.existsCategoryByNameAndParentCategory_Id(name, parentCategoryId);
    }
    private Category findByPosition(Integer position) {
        return (Category)this.categoryRepository.findByPosition(position).orElseThrow(() -> new NotFoundException("There is no category at this position: " + position));
    }
}
