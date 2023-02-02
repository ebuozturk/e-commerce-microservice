
package com.ebuozturk.images.service;

import com.ebuozturk.images.config.ImageUrls;
import com.ebuozturk.images.config.S3BucketPath;
import com.ebuozturk.images.converter.ProductImageConverter;
import com.ebuozturk.images.dto.ProductImageDto;
import com.ebuozturk.images.dto.UpdateProductImageRequest;
import com.ebuozturk.images.exception.ProductImageNotFoundException;
import com.ebuozturk.images.model.ProductImage;
import com.ebuozturk.images.repository.ProductImageRepository;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductImageService {
    private final S3Service s3Service;
    private final ImageUrls imageUrl;
    private final S3BucketPath s3BucketPath;
    private final ProductImageRepository productImageRepository;
    private final ProductImageConverter productImageConverter;

    public ProductImageService(S3Service s3Service, ImageUrls imageUrl, S3BucketPath s3BucketPath, ProductImageRepository productImageRepository, ProductImageConverter productImageConverter) {
        this.s3Service = s3Service;
        this.imageUrl = imageUrl;
        this.s3BucketPath = s3BucketPath;
        this.productImageRepository = productImageRepository;
        this.productImageConverter = productImageConverter;
    }

    public List<ProductImageDto> getAllImagesByProductId(String productId) {
        return this.productImageConverter.convert(this.productImageRepository.findAllByProductIdOrderByPosition(productId));
    }

    public ProductImageDto getCoverImageByProductId(String productId) {
        return this.productImageConverter.convert(this.findCoverImageByProductId(productId));
    }

    public ProductImageDto changeCoverImage(String key) {
        ProductImage newCoverImage = this.findByKey(key);
        ProductImage oldCoverImage = this.findCoverImageByProductId(newCoverImage.getProductId());
        this.productImageRepository.save(new ProductImage(oldCoverImage.getKey(), oldCoverImage.getProductId(), newCoverImage.getPosition(), oldCoverImage.getSrc()));
        return this.productImageConverter.convert((ProductImage)this.productImageRepository.save(new ProductImage(newCoverImage.getKey(), newCoverImage.getProductId(), 1, newCoverImage.getSrc())));
    }

    public void uploadImage(String productId, List<MultipartFile> files) throws IOException {
        files.forEach((file) -> {
            String filePath = String.format("%s/%s", this.s3BucketPath.getProduct(), productId);
            String fileName = String.format("%s-%s", file.getName(), UUID.randomUUID());
            String fileUrl = this.imageUrl.getProduct() + "/" + productId + "/" + fileName;
            Integer position = this.productImageRepository.countByProductId(productId) + 1;

            try {
                this.s3Service.save(filePath, fileName, file);
                this.productImageRepository.save(new ProductImage(fileName, productId, position, fileUrl));
            } catch (Exception var8) {
                throw new IllegalStateException(var8);
            }
        });
    }

    public ProductImageDto updateImage(String key, UpdateProductImageRequest request) {
        ProductImage productImage = this.findByKey(key);
        return this.productImageConverter.convert((ProductImage)this.productImageRepository.save(new ProductImage(productImage.getKey(), request.getProductId(), request.getPosition(), request.getSrc())));
    }

    public void deleteImage(String productId, String key) {
        ProductImage productImage = this.findByKey(key);
        this.productImageRepository.delete(productImage);
        String filePath = String.format("%s/%s", this.s3BucketPath.getProduct(), productId);
        this.s3Service.delete(filePath, key);
    }

    public byte[] downloadImage(String productId, String key) {
        String path = String.format("%s/%s", this.s3BucketPath.getProduct(), productId);
        return this.s3Service.download(path, key);
    }

    protected ProductImage findCoverImageByProductId(String productId) {
        return (ProductImage)this.productImageRepository.findFirstByProductIdOrderByPosition(productId).orElseThrow(() -> {
            return new ProductImageNotFoundException("The image is not found by following product id: " + productId);
        });
    }

    protected ProductImage findByKey(String key) {
        return (ProductImage)this.productImageRepository.findByKey(key).orElseThrow(() -> {
            return new ProductImageNotFoundException("The image is not found by following key: " + key);
        });
    }
}