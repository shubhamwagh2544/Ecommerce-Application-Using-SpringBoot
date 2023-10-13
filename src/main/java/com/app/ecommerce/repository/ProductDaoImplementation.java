package com.app.ecommerce.repository;

import com.app.ecommerce.exception.ResourceNotFoundException;
import com.app.ecommerce.model.Product;
import com.app.ecommerce.model.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("product-jpa")
public class ProductDaoImplementation implements ProductDao {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean existsProductByName(String productName) {
        return getAllProducts().stream()
                .anyMatch(product -> product.getProductName().equals(productName));
    }

    @Override
    public String createProduct(ProductRequest productRequest) {
        Product product = new Product(
                productRequest.productName(),
                productRequest.description(),
                productRequest.price(),
                productRequest.isAvailable()
        );

        productRepository.save(product);
        return "success";
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long productId) {
        return getAllProducts().stream()
                .filter(product -> product.getProductId() == productId)
                .findAny()
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format("Product with id %s not found", productId)
                        )
                );
    }

    @Override
    public String updateProduct(Long productId, ProductRequest productRequest) {
        Product product = getProduct(productId);
        boolean flag = false;

        if (!product.getProductName().equals(productRequest.productName())) {
            product.setProductName(productRequest.productName());
            flag = true;
        }
        if (!product.getDescription().equals(productRequest.description())) {
            product.setDescription(productRequest.description());
            flag = true;
        }
        if (product.getPrice() != productRequest.price()) {
            product.setPrice(productRequest.price());
            flag = true;
        }
        if (product.isAvailable() != productRequest.isAvailable()) {
            product.setAvailable(productRequest.isAvailable());
            flag = true;
        }
        if (product.getDateBought() != productRequest.dateBought()) {
            product.setDateBought(productRequest.dateBought());
            flag = true;
        }
        if (product.getCategory() != productRequest.category()) {
            product.setCategory(productRequest.category());
            flag = true;
        }

        return !flag ? "success : no change" : "success : updated";
    }

    @Override
    public String deleteProduct(Long productId) {
        Product product = getProduct(productId);
        productRepository.delete(product);
        return "success";
    }
}
