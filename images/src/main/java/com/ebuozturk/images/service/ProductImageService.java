package com.ebuozturk.images.service;

import com.ebuozturk.images.config.ImageUrls;
import com.ebuozturk.images.config.S3BucketPath;
import com.ebuozturk.images.converter.ProductImageConverter;
import com.ebuozturk.images.dto.ProductImageDto;
import com.ebuozturk.images.dto.UpdateProductImageRequest;
import com.ebuozturk.images.exception.ProductImageNotFoundException;
import com.ebuozturk.images.model.ProductImage;
import com.ebuozturk.images.repository.ProductImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


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

        return productImageConverter.convert(productImageRepository.findAllByProductIdOrderByPosition(productId));
    }

    public ProductImageDto getCoverImageByProductId(String productId){
        return productImageConverter.convert(findCoverImageByProductId(productId));
    }

    public ProductImageDto changeCoverImage(String key){
        ProductImage newCoverImage = findByKey(key);

        ProductImage oldCoverImage = findCoverImageByProductId(newCoverImage.getProductId());

        productImageRepository.save(new ProductImage(
                oldCoverImage.getKey(),
                oldCoverImage.getProductId(),
                newCoverImage.getPosition(),
                oldCoverImage.getSrc()
        ));
        return  productImageConverter.convert(productImageRepository.save(new ProductImage(
                    newCoverImage.getKey(),
                    newCoverImage.getProductId(),
                    1,
                    newCoverImage.getSrc()
        )));
    }

    public void uploadImage(String productId, List<MultipartFile> files) throws IOException {
        files.forEach(file ->{
            String filePath = String.format("%s/%s",s3BucketPath.getProduct(),productId);
            String fileName = String.format("%s-%s",file.getName(), UUID.randomUUID());
            String fileUrl = imageUrl.getProduct() + "/" + productId + "/" + fileName;
            Integer position = productImageRepository.countByProductId(productId)+1;
            try{
                s3Service.save(filePath,fileName,file);
                productImageRepository.save(new ProductImage(fileName,productId,position,fileUrl));

            }catch (Exception e){
                throw new IllegalStateException(e);
            }
        });


    }

    public ProductImageDto updateImage(String key, UpdateProductImageRequest request){
        ProductImage productImage = findByKey(key);
        return productImageConverter.convert(productImageRepository.save(new ProductImage(
                productImage.getKey(),
                request.getProductId(),
                request.getPosition(),
                request.getSrc()
        )));
    }

    public void deleteImage(String productId, String key) {

        ProductImage productImage = findByKey(key);
        productImageRepository.delete(productImage);
        String filePath = String.format("%s/%s",s3BucketPath.getProduct(),productId);
        s3Service.delete(filePath,key);

    }

    public byte[] downloadImage(String productId,String key){
        String path = String.format("%s/%s",s3BucketPath.getProduct(),productId);
        return s3Service.download(path,key);
    }

    protected ProductImage findCoverImageByProductId(String productId){
        return productImageRepository.findFirstByProductIdOrderByPosition(productId)
                .orElseThrow(()-> new ProductImageNotFoundException("The image is not found by following product id: "+productId));
    }
    protected ProductImage findByKey(String key){
        return productImageRepository.findByKey(key)
                .orElseThrow(()-> new ProductImageNotFoundException("The image is not found by following key: "+key));
    }

}
