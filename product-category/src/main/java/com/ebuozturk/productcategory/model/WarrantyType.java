package com.ebuozturk.productcategory.model;

import com.ebuozturk.productcategory.exception.NotFoundException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public enum WarrantyType
{
    IMPORTER, DISTRIBUTOR, NULL;

    private static final Map<String, WarrantyType> nameToValueMap;

    static {
        nameToValueMap = new HashMap<>();

        for(WarrantyType value: EnumSet.allOf(WarrantyType.class))
            nameToValueMap.put(value.name(), value);

    }

    public static WarrantyType getTypeByName(String name) {
        WarrantyType type = nameToValueMap.get(String.format(name, new Object[0]).toUpperCase());
        if (type != null) {
            return type;
        }
        throw new NotFoundException(name + " warranty type does not exist");
    }
}
