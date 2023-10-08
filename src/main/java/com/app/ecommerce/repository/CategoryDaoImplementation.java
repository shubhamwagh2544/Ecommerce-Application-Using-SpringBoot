package com.app.ecommerce.repository;

import com.app.ecommerce.exception.CategoryNotFoundException;
import com.app.ecommerce.model.Category;
import com.app.ecommerce.model.CategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("jpa")
public class CategoryDaoImplementation implements CategoryDao {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public boolean existsCategoryByName(String categoryName) {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .anyMatch(category -> category.getCategoryName().equals(categoryName));
    }

    @Override
    public String createCategory(CategoryRequest categoryRequest) {

        Category category = new Category(
                categoryRequest.getCategoryName(),
                categoryRequest.getDescription()
        );

        categoryRepository.save(category);
        return "success";
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                String.format("category with id %s not found", categoryId)
                        )
                );
    }

    @Override
    public String updateCategory(Long categoryId, CategoryRequest categoryRequest) {
        Category category = getCategory(categoryId);
        boolean flag = false;
        if (categoryRequest.getCategoryName() != category.getCategoryName()) {
            category.setCategoryName(categoryRequest.getCategoryName());
            flag = true;
        }
        if (categoryRequest.getDescription() != category.getDescription()) {
            category.setDescription(categoryRequest.getDescription());
            flag = true;
        }
        categoryRepository.save(category);

        return !flag ? "success : no change" : "success : updated";
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = getCategory(categoryId);
        categoryRepository.delete(category);
        return "success";
    }
}
