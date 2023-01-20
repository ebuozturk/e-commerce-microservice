
package com.ebuozturk.productcategory.service;

import com.ebuozturk.productcategory.converter.FeatureTypeConverter;
import com.ebuozturk.productcategory.dto.featureType.CreateFeatureTypeRequest;
import com.ebuozturk.productcategory.dto.featureType.FeatureTypeDto;
import com.ebuozturk.productcategory.dto.featureType.UpdateFeatureTypeRequest;
import com.ebuozturk.productcategory.exception.NotFoundException;
import com.ebuozturk.productcategory.model.Category;
import com.ebuozturk.productcategory.model.FeatureType;
import com.ebuozturk.productcategory.repository.FeatureTypeRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;



@Service
public class FeatureTypeService
{
    private final FeatureTypeRepository featureTypeRepository;
    private final FeatureTypeConverter featureTypeConverter;
    private final CategoryService categoryService;

    public FeatureTypeService(FeatureTypeRepository featureTypeRepository, FeatureTypeConverter featureTypeConverter, CategoryService categoryService) {
        this.featureTypeRepository = featureTypeRepository;
        this.featureTypeConverter = featureTypeConverter;
        this.categoryService = categoryService;
    }

    public FeatureTypeDto createFeatureType(CreateFeatureTypeRequest request) {
        Category category = this.categoryService.findById(request.categoryId());
        return this.featureTypeConverter.convert((FeatureType)this.featureTypeRepository.save(new FeatureType(request.name(), category, LocalDateTime.now(), LocalDateTime.now())));
    } public FeatureTypeDto updateFeatureType(String id, UpdateFeatureTypeRequest request) {
    Category category;
    FeatureType existFeatureType = findById(id);



    if (!Objects.isNull(request.categoryId())) {


        category = existFeatureType.getCategory().getId().equals(request.categoryId()) ? existFeatureType.getCategory() : this.categoryService.findById(request.categoryId());
    } else {
        category = existFeatureType.getCategory();
    }
    FeatureType featureType = new FeatureType(existFeatureType.getId(), existFeatureType.getName(), category, existFeatureType.getCreatedAt(), LocalDateTime.now());

    return this.featureTypeConverter.convert((FeatureType)this.featureTypeRepository.save(featureType));
}

    public List<FeatureTypeDto> getAll() {
        return this.featureTypeConverter.convert(this.featureTypeRepository.findAll());
    }
    public List<FeatureTypeDto> getAllByCategoryId(String id) {
        return this.featureTypeConverter.convert(this.featureTypeRepository.findFeatureTypeByCategory_id(id));
    }
    public FeatureTypeDto getById(String id) {
        return this.featureTypeConverter.convert(findById(id));
    }
    public Boolean deleteById(String id) {
        FeatureType featureType = findById(id);
        try {
            this.featureTypeRepository.delete(featureType);
            return Boolean.valueOf(true);
        } catch (Exception e) {
            return Boolean.valueOf(false);
        }
    }
    protected FeatureType findById(String id) {
        return (FeatureType)this.featureTypeRepository.findById(id).orElseThrow(() -> new NotFoundException("The Feature Type could not be found by following id: " + id));
    }
    protected FeatureType findFeatureTypeById(String id) {
        return (FeatureType)this.featureTypeRepository.findFeatureTypeById(id).orElseThrow(() -> new NotFoundException("The Feature Type could not be found by following id: " + id));
    }
}
