package com.app.ecommerce.controller;

import com.app.ecommerce.model.Category;
import com.app.ecommerce.model_request.CategoryRequest;
import com.app.ecommerce.service.CategoryService;
import com.app.ecommerce.utility.ApiResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/category")
@Api(tags = "Category-Controller")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse> createCategory(@RequestBody CategoryRequest categoryRequest) {
        String message = categoryService.createCategory(categoryRequest);
        return new ResponseEntity<>(
                new ApiResponse(true, message, LocalDateTime.now()),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<>(
                categoryService.getAllCategories(), HttpStatus.OK
        );
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable Long categoryId) {
        return new ResponseEntity<>(
                categoryService.getCategory(categoryId), HttpStatus.OK
        );
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryRequest categoryRequest) {
        String message = categoryService.updateCategory(categoryId, categoryRequest);
        return new ResponseEntity<>(
                new ApiResponse(true, message, LocalDateTime.now()), HttpStatus.OK
        );
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long categoryId) {
        String message = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(
                new ApiResponse(true, message, LocalDateTime.now()), HttpStatus.OK
        );
    }
}
