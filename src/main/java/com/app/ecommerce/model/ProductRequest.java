package com.app.ecommerce.model;

import java.time.LocalDateTime;

public record ProductRequest(
        String productName,
        String description,
        double price,
        boolean isAvailable,
        LocalDateTime dateBought,
        Category category
) {
}
