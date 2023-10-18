package com.app.ecommerce.model_request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CartUpdateRequest {
    private long productId;
    private LocalDateTime createdAt;
    private long quantity;
}
