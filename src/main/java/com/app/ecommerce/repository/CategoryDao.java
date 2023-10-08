package com.app.ecommerce.repository;

import com.app.ecommerce.model.Category;
import com.app.ecommerce.model.CategoryRequest;

import java.util.List;

public interface CategoryDao {
    public boolean existsCategoryByName(String categoryName);
    public String createCategory(CategoryRequest categoryRequest);
    public List<Category> getAllCategories();
    public Category getCategory(Long categoryId);
    public String updateCategory(Long categoryId, CategoryRequest categoryRequest);
    public String deleteCategory(Long categoryId);

}
