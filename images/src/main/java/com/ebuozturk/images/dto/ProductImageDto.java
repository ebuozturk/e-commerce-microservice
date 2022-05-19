package com.ebuozturk.images.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductImageDto(@JsonProperty("key") String key,
                              @JsonProperty("productId") String productId,
                              @JsonProperty("position") Integer position,
                              @JsonProperty("src") String src) {


}
