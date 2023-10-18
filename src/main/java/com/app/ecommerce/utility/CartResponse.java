package com.app.ecommerce.utility;

import com.app.ecommerce.model.Product;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {
    private long id;
    private LocalDateTime createdAt;
    private Product product;
    private long quantity;
    private double totalCost;
}
