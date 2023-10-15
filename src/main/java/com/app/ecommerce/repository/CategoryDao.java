package com.app.ecommerce.repository;

import com.app.ecommerce.model.Category;
import com.app.ecommerce.model_request.CategoryRequest;

import java.util.List;

public interface CategoryDao {
    boolean existsCategoryByName(String categoryName);
    String createCategory(CategoryRequest categoryRequest);
    List<Category> getAllCategories();
    Category getCategory(Long categoryId);
    String updateCategory(Long categoryId, CategoryRequest categoryRequest);
    String deleteCategory(Long categoryId);

}
