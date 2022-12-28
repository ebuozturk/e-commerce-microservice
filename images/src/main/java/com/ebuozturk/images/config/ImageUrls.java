package com.ebuozturk.images.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "image.url")
@Component
public class ImageUrls {
    private String product;
    private String user;

    public ImageUrls() {
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
