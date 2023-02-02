package com.ebuozturk.images.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {
    public AmazonConfig() {
    }

    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCredentials = new BasicAWSCredentials("AKIAXGN74OQFIZ6MVMHA", "IHrycv+0T/92jOAG50R6hJIxyLZTuMWO+B8j7E3A");
        return (AmazonS3)((AmazonS3ClientBuilder)((AmazonS3ClientBuilder)AmazonS3Client.builder().withRegion("eu-central-1")).withCredentials(new AWSStaticCredentialsProvider(awsCredentials))).build();
    }
}
