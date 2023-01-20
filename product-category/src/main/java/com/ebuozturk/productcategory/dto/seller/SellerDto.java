package com.ebuozturk.productcategory.dto.seller;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SellerDto(String id, String firstName, String lastName, String email) {
    public SellerDto(@JsonProperty("id") String id, @JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName, @JsonProperty("email") String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @JsonProperty("id")
    public String id() {
        return this.id;
    }

    @JsonProperty("firstName")
    public String firstName() {
        return this.firstName;
    }

    @JsonProperty("lastName")
    public String lastName() {
        return this.lastName;
    }

    @JsonProperty("email")
    public String email() {
        return this.email;
    }
}
