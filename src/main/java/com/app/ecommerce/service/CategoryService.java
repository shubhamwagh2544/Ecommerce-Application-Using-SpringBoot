package com.app.ecommerce.service;

import com.app.ecommerce.model.Category;
import com.app.ecommerce.model.CategoryRequest;
import com.app.ecommerce.repository.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    @Qualifier("category-jpa")
    private CategoryDao categoryDao;

    public String createCategory(CategoryRequest categoryRequest) {
        return categoryDao.createCategory(categoryRequest);
    }

    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    public Category getCategory(Long categoryId) {
        return categoryDao.getCategory(categoryId);
    }

    public String updateCategory(Long categoryId, CategoryRequest categoryRequest) {
        return categoryDao.updateCategory(categoryId, categoryRequest);
    }

    public String deleteCategory(Long categoryId) {
        return categoryDao.deleteCategory(categoryId);
    }
}
