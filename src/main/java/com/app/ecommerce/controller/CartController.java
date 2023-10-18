package com.app.ecommerce.controller;

import com.app.ecommerce.model.Cart;
import com.app.ecommerce.model_request.CartRequest;
import com.app.ecommerce.model_request.CartUpdateRequest;
import com.app.ecommerce.service.CartService;
import com.app.ecommerce.utility.ApiResponse;
import com.app.ecommerce.utility.CartResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/cart")
@Api(tags = "Cart-Controller")
public class CartController {
    
    @Autowired
    private CartService cartService;

    @PostMapping("/addItem")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestBody CartRequest cartRequest,
                                                     @RequestParam("token") String token) {
        String message = cartService.addItemToCart(cartRequest, token);
        return new ResponseEntity<>(
                new ApiResponse(true, message, LocalDateTime.now()), HttpStatus.CREATED
        );
    }

    @GetMapping("/get")
    public ResponseEntity<List<CartResponse>> getAllCartItems(@RequestParam("token") String token) {
        return new ResponseEntity<>(
                cartService.getCartForUser(token),
                HttpStatus.OK
        );
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable long cartId,
                                        @RequestParam("token") String token) {
        return new ResponseEntity<>(
                cartService.getCartByCartId(cartId, token),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/cartId")
    public ResponseEntity<ApiResponse> deleteCart(@RequestParam("token") String token) {
        String message = cartService.deleteCartForUser(token);
        return new ResponseEntity<>(
                new ApiResponse(true, message, LocalDateTime.now()),
                HttpStatus.OK
        );
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateCart(@RequestBody CartUpdateRequest updateRequest,
                                                  @RequestParam("token") String token) {
        String message = cartService.updateCart(updateRequest, token);
        return new ResponseEntity<>(
                new ApiResponse(true, message, LocalDateTime.now()), HttpStatus.OK
        );
    }
}
