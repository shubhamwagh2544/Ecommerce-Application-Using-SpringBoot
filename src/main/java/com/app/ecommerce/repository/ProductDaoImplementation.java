package com.app.ecommerce.repository;

import com.app.ecommerce.exception.ResourceNotFoundException;
import com.app.ecommerce.model.Category;
import com.app.ecommerce.model.Product;
import com.app.ecommerce.model_request.CategoryRequest;
import com.app.ecommerce.model_request.ProductRequest;
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
                productRequest.getProductName(),
                productRequest.getDescription(),
                productRequest.getPrice(),
                productRequest.isAvailable()
        );

        if (productRequest.getCategoryRequest() != null) {
            product.setCategory(
                    new Category(
                            productRequest.getCategoryRequest().getCategoryName(),
                            productRequest.getCategoryRequest().getDescription()
                    )
            );
        }

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

        if (!product.getProductName().equals(productRequest.getProductName())) {
            product.setProductName(productRequest.getProductName());
            flag = true;
        }
        if (!product.getDescription().equals(productRequest.getDescription())) {
            product.setDescription(productRequest.getDescription());
            flag = true;
        }
        if (product.getPrice() != productRequest.getPrice()) {
            product.setPrice(productRequest.getPrice());
            flag = true;
        }
        if (product.isAvailable() != productRequest.isAvailable()) {
            product.setAvailable(productRequest.isAvailable());
            flag = true;
        }
        if (product.getDateBought() != productRequest.getDateBought()) {
            product.setDateBought(productRequest.getDateBought());
            flag = true;
        }

        if (product.getCategory() != null && productRequest.getCategoryRequest() != null) {
            Category category = product.getCategory();
            CategoryRequest categoryRequest = productRequest.getCategoryRequest();
            if (!categoryRequest.getCategoryName().equals(category.getCategoryName())) {
                category.setCategoryName(categoryRequest.getCategoryName());
                flag = true;
            }
            if (!categoryRequest.getDescription().equals(category.getDescription())) {
                category.setDescription(categoryRequest.getDescription());
                flag = true;
            }
            if (flag) product.setCategory(category);
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
