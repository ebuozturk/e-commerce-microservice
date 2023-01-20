
package com.ebuozturk.productcategory.converter;

import com.ebuozturk.productcategory.dto.feature.FeatureDto;
import com.ebuozturk.productcategory.model.Feature;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class FeatureConverter
{
    @Autowired
    private FeatureTypeConverter featureTypeConverter;

    public FeatureDto convert(Feature feature) {
        return new FeatureDto(feature.getId(), feature.getName(), this.featureTypeConverter.convert(feature.getFeatureType()));
    }

    public List<FeatureDto> convert(Set<Feature> featureSet) {
        return (List<FeatureDto>)featureSet.stream().map(this::convert).collect(Collectors.toList());
    }
    public List<FeatureDto> convert(List<Feature> featureList) {
        return (List<FeatureDto>)featureList.stream().map(this::convert).collect(Collectors.toList());
    }
}
