package com.app.ecommerce.controller;

import com.app.ecommerce.model.Checkout;
import com.app.ecommerce.service.CheckoutService;
import com.app.ecommerce.utility.CheckoutResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/checkout")
@Api(tags = "Checkout-Controller")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @PostMapping("/{cartId}")
    public ResponseEntity<CheckoutResponse> addCartToCheckout(@PathVariable long cartId,
                                                              @RequestParam("token") String token) {
        return new ResponseEntity<>(
                checkoutService.addCartToCheckout(cartId, token),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/get")
    public ResponseEntity<List<Checkout>> getCheckoutForUser(@RequestParam("token") String token) {
        return new ResponseEntity<>(
                checkoutService.getCheckoutForUser(token),
                HttpStatus.OK
        );
    }
}
