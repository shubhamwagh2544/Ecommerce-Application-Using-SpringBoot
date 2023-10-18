package com.app.ecommerce.controller;

import com.app.ecommerce.model.Product;
import com.app.ecommerce.model.User;
import com.app.ecommerce.model.WishList;
import com.app.ecommerce.service.TokenService;
import com.app.ecommerce.service.WishListService;
import com.app.ecommerce.utility.ApiResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/wishlist")
@Api(tags = "WishList-Controller")
public class WishListController {
    @Autowired
    private WishListService wishListService;


    @PostMapping("/addItem")
    public ResponseEntity<ApiResponse> addProductToWishList(@RequestBody Product product,
                                                            @RequestParam("token") String token) {
        String message = wishListService.addItemToWishList(product, token);
        return new ResponseEntity<>(
                new ApiResponse(true, message, LocalDateTime.now()), HttpStatus.CREATED
        );
    }

    @GetMapping("/{token}")
    public ResponseEntity<List<Product>> getWishList(@PathVariable String token) {
        List<Product> products = wishListService.getWishList(token);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
