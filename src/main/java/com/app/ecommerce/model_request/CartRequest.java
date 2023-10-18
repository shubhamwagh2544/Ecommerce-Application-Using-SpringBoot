package com.app.ecommerce.model_request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequest {
    private long productId;
    private long quantity;
}
