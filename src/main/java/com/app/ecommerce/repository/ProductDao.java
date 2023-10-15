package com.app.ecommerce.repository;

import com.app.ecommerce.model.Product;
import com.app.ecommerce.model_request.ProductRequest;

import java.util.List;

public interface ProductDao {
    boolean existsProductByName(String productName);
    String createProduct(ProductRequest productRequest);
    List<Product> getAllProducts();
    Product getProduct(Long productId);
    String updateProduct(Long productId, ProductRequest productRequest);
    String deleteProduct(Long productId);
}
