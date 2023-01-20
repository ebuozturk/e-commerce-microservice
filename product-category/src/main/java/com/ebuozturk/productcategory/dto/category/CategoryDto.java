package com.ebuozturk.productcategory.dto.category;

public class CategoryDto
{
    private String id;
    private String name;
    private Integer position;

    public CategoryDto() {}

    public CategoryDto(String id, String name, Integer position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Integer getPosition() {
        return this.position;
    }
}
