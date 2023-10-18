package com.app.ecommerce.utility;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CheckoutResponse {
    private String message;
    private double cost;
    private String UPI;

    public CheckoutResponse(double cost) {
        this.message = "Please pay the amount below to UPI ID";
        this.cost = cost;
        this.UPI = "8169747165@hdfcbank";
    }
}
