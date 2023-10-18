package com.app.ecommerce.controller;

import com.app.ecommerce.model.Product;
import com.app.ecommerce.model_request.ProductRequest;
import com.app.ecommerce.service.ProductService;
import com.app.ecommerce.utility.ApiResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@Api(tags = "Product-Controller")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductRequest productRequest) {
        String message = productService.createProduct(productRequest);
        return new ResponseEntity<>(
                new ApiResponse(true, message, LocalDateTime.now()), HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
        return new ResponseEntity<>(
                productService.getProduct(productId), HttpStatus.OK
        );
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long productId,
                                                     @RequestBody ProductRequest productRequest) {
        String message = productService.updateProduct(productId, productRequest);
        return new ResponseEntity<>(
                new ApiResponse(true, message, LocalDateTime.now()), HttpStatus.OK
        );
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) {
        String message = productService.deleteProduct(productId);
        return new ResponseEntity<>(
                new ApiResponse(true, message, LocalDateTime.now()), HttpStatus.OK
        );
    }

}
