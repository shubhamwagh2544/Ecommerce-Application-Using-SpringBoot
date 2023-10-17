package com.app.ecommerce.controller;

import com.app.ecommerce.model.Product;
import com.app.ecommerce.model.User;
import com.app.ecommerce.service.TokenService;
import com.app.ecommerce.service.WishListService;
import com.app.ecommerce.utility.ApiResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/wishlist")
@Api(tags = "WishList Controller")
public class WishListController {

    @Autowired
    private WishListService wishListService;
    @Autowired
    private TokenService tokenService;

    public ResponseEntity<ApiResponse> addProductToWishList(@RequestBody Product product,
                                                            @RequestParam("token") String token) {
        //authenticate token
        tokenService.authenticate(token);

        //find user
        User user = tokenService.getUser(token);

        //save product to wishlist
        
    }
}
