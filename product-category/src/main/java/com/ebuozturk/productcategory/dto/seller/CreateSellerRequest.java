package com.ebuozturk.productcategory.dto.seller;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateSellerRequest(String firstName, String lastName, String email) {
    public CreateSellerRequest(@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName, @JsonProperty("email") String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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
