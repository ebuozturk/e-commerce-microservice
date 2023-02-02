package com.ebuozturk.images.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(
        prefix = "bucket.path"
)
@Component
public class S3BucketPath {
    private String product;

    public S3BucketPath() {
    }

    public String getProduct() {
        return this.product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
