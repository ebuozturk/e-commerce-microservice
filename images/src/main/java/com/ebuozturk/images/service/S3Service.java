package com.ebuozturk.images.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class S3Service {
    private final AmazonS3 s3;

    public S3Service(AmazonS3 s3) {
        this.s3 = s3;
    }

    public boolean save(String path, String fileName, MultipartFile file) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        try {
            this.s3.putObject(path, fileName, file.getInputStream(), metadata);
            return true;
        } catch (AmazonServiceException var6) {
            throw new IllegalStateException(var6);
        }
    }

    public void delete(String bucketName, String key) {
        try {
            this.s3.deleteObject(new DeleteObjectRequest(bucketName, key));
        } catch (AmazonServiceException var4) {
            var4.printStackTrace();
        } catch (SdkClientException var5) {
            var5.printStackTrace();
        }

    }

    public byte[] download(String path, String key) {
        try {
            S3Object object = this.s3.getObject(path, key);
            return IOUtils.toByteArray(object.getObjectContent());
        } catch (IOException | AmazonServiceException var4) {
            throw new IllegalStateException("Failed to download file to s3: " + var4);
        }
    }
}