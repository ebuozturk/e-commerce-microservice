package com.ebuozturk.productcategory.dto.brand;

public  class BrandDto {

    private final String id;

    private final String name;

    public BrandDto(String id,String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public String toString() {
        return "BrandDto(id=" + this.id + ", name=" + this.name + ')';
    }

    public int hashCode() {
        int result = this.id.hashCode();
        result = result * 31 + this.name.hashCode();
        return result;
    }

}
