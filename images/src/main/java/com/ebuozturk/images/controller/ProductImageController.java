
package com.ebuozturk.images.controller;

import com.ebuozturk.images.dto.ProductImageDto;
import com.ebuozturk.images.dto.UpdateProductImageRequest;
import com.ebuozturk.images.service.ProductImageService;
import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping({"/v1/images/product"})
@CrossOrigin({"*"})
public class ProductImageController {
    private final ProductImageService productImageService;

    public ProductImageController(ProductImageService productImageService) {
        this.productImageService = productImageService;
    }

    @GetMapping({"{productId}/{key}"})
    public ResponseEntity<byte[]> downloadImage(@PathVariable String productId, @PathVariable String key) {
        return ResponseEntity.ok(this.productImageService.downloadImage(productId, key));
    }

    @GetMapping({"{productId}"})
    public ResponseEntity<List<ProductImageDto>> getAllImages(@PathVariable String productId) {
        return ResponseEntity.ok(this.productImageService.getAllImagesByProductId(productId));
    }

    @GetMapping({"/coverImage/{productId}"})
    public ResponseEntity<ProductImageDto> getCoverImage(@PathVariable String productId) {
        return ResponseEntity.ok(this.productImageService.getCoverImageByProductId(productId));
    }

    @PutMapping({"/coverImage/{key}"})
    public ResponseEntity<ProductImageDto> changeCoverImage(@PathVariable String key) {
        return ResponseEntity.ok(this.productImageService.changeCoverImage(key));
    }

    @PutMapping({"{key}"})
    public ResponseEntity<ProductImageDto> updateProductImage(@PathVariable String key, @RequestBody UpdateProductImageRequest request) {
        return ResponseEntity.ok(this.productImageService.updateImage(key, request));
    }

    @PostMapping({"{productId}"})
    public ResponseEntity<String> uploadImage(@PathVariable String productId, @RequestParam("file") List<MultipartFile> files) throws IOException {
        this.productImageService.uploadImage(productId, files);
        return new ResponseEntity("Images are uploaded successfully!", HttpStatus.CREATED);
    }

    @DeleteMapping({"{productId}/{key}"})
    public ResponseEntity<String> deleteImage(@PathVariable String productId, @PathVariable String key) {
        this.productImageService.deleteImage(productId, key);
        return new ResponseEntity("Image is deleted successfully!", HttpStatus.OK);
    }
}