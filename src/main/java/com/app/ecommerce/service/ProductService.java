package com.app.ecommerce.service;

import com.app.ecommerce.model.Product;
import com.app.ecommerce.model.ProductRequest;
import com.app.ecommerce.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    @Qualifier("product-jpa")
    private ProductDao productDao;

    public boolean existsProductByName(String productName) {
        return getAllProducts().stream()
                .anyMatch(product -> product.getProductName().equals(productName));
    }

    public String createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public Product getProduct(Long productId) {
        return productDao.getProduct(productId);
    }

    public String updateProduct(Long productId, ProductRequest productRequest) {
        return productDao.updateProduct(productId, productRequest);
    }

    public String deleteProduct(Long productId) {
        return productDao.deleteProduct(productId);
    }
}
