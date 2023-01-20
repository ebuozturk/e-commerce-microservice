
package com.ebuozturk.productcategory.service;

import com.ebuozturk.productcategory.converter.FeatureConverter;
import com.ebuozturk.productcategory.dto.feature.CreateFeatureRequest;
import com.ebuozturk.productcategory.dto.feature.FeatureDto;
import com.ebuozturk.productcategory.dto.feature.UpdateFeatureRequest;
import com.ebuozturk.productcategory.exception.NotFoundException;
import com.ebuozturk.productcategory.model.Category;
import com.ebuozturk.productcategory.model.Feature;
import com.ebuozturk.productcategory.model.FeatureType;
import com.ebuozturk.productcategory.repository.FeatureRepository;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;


@Service
public class FeatureService
{
    private final FeatureRepository featureRepository;
    private final FeatureConverter featureConverter;
    private final FeatureTypeService featureTypeService;
    private final CategoryService categoryService;

    public FeatureService(FeatureRepository featureRepository, FeatureConverter featureConverter, FeatureTypeService featureTypeService, CategoryService categoryService) {
        this.featureRepository = featureRepository;
        this.featureConverter = featureConverter;
        this.featureTypeService = featureTypeService;
        this.categoryService = categoryService;
    }


    public FeatureDto createFeature(CreateFeatureRequest request) {
        FeatureType featureType = this.featureTypeService.findById(request.featureTypeId());
        Set<Category> categoryList = new HashSet<>();
        this.categoryService.findCategorySetByIdList(request.categoryIdList()).stream().forEach(category -> categoryList.add(category));
        Feature feature = new Feature(request.name(), featureType, categoryList);
        feature.setCreatedAt(LocalDateTime.now());
        feature.setUpdatedAt(LocalDateTime.now());

        return this.featureConverter.convert((Feature)this.featureRepository.save(feature));
    }

    public FeatureDto updateFeature(String id, UpdateFeatureRequest request) {
        Feature existFeature = findById(id);



        FeatureType featureType = existFeature.getFeatureType().getId().equals(request.featureTypeId()) ? existFeature.getFeatureType() : this.featureTypeService.findById(request.featureTypeId());

        Set<Category> categoryList = this.categoryService.findCategorySetByIdList(request.categoryIdList());

        Feature feature = new Feature(id, request.name(), featureType, categoryList);
        feature.setCreatedAt(existFeature.getCreatedAt());
        feature.setUpdatedAt(LocalDateTime.now());

        return this.featureConverter.convert((Feature)this.featureRepository.save(feature));
    }

    public Boolean deleteFeature(String id) {
        Feature feature = findById(id);
        try {
            this.featureRepository.delete(feature);
            return Boolean.valueOf(true);
        } catch (Exception e) {
            return Boolean.valueOf(false);
        }
    }

    public List<FeatureDto> getAll() {
        return this.featureConverter.convert(this.featureRepository.findAll());
    }
    public FeatureDto getById(String id) {
        return this.featureConverter.convert(findById(id));
    }
    public List<FeatureDto> getByFeatureTypeId(String featureTypeId) {
        return this.featureConverter.convert(this.featureRepository.findByFeatureType_id(featureTypeId));
    }
    protected Feature findById(String id) {
        return (Feature)this.featureRepository.findById(id).orElseThrow(() -> new NotFoundException("The feature could not be found by following id: " + id));
    }
}
