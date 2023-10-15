package com.app.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String productName;
    private String description;
    private double price;
    private boolean isAvailable;
    private LocalDateTime dateBought;
    private CategoryRequest categoryRequest;

    public ProductRequest(String productName, String description, double price, boolean isAvailable, LocalDateTime dateBought) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
        this.dateBought = dateBought;
    }
}
