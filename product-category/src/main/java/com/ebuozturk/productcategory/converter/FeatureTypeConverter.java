package com.ebuozturk.productcategory.converter;

import com.ebuozturk.productcategory.dto.featureType.FeatureTypeDto;
import com.ebuozturk.productcategory.model.FeatureType;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;


@Component
public class FeatureTypeConverter
{
    public FeatureTypeDto convert(FeatureType featureType) {
        return new FeatureTypeDto(featureType.getId(), featureType.getName());
    }

    public List<FeatureTypeDto> convert(Set<FeatureType> featureTypeSet) {
        return (List<FeatureTypeDto>)featureTypeSet.stream().map(this::convert).collect(Collectors.toList());
    }
    public List<FeatureTypeDto> convert(List<FeatureType> featureTypeSet) {
        return (List<FeatureTypeDto>)featureTypeSet.stream().map(this::convert).collect(Collectors.toList());
    }
}