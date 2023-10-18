package com.app.ecommerce.service;

import com.app.ecommerce.exception.ResourceDuplicationException;
import com.app.ecommerce.model.Product;
import com.app.ecommerce.model.User;
import com.app.ecommerce.model.WishList;
import com.app.ecommerce.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService {

    @Autowired
    private WishListRepository wishListRepository;
    @Autowired
    private TokenService tokenService;

    public boolean checkIfProductExistsInWishList(Product product) {
        return wishListRepository.existsByProduct(product);
    }

    public String addItemToWishList(Product product, String token) {
        //authenticate token
        tokenService.authenticate(token);

        //find user
        User user = tokenService.getUser(token);

        if (checkIfProductExistsInWishList(product)) {
            throw new ResourceDuplicationException("Product already present in wishlist");
        }

        //save product to wishlist
        WishList wishList = new WishList(user, product);
        this.wishListRepository.save(wishList);

        return "Product added to wishlist successfully";
    }

    public List<Product> getWishList(String token) {
        //authenticate token
        tokenService.authenticate(token);

        //find user
        User user = tokenService.getUser(token);

        List<WishList> wishLists = wishListRepository.findAllByUserOrderByCreatedAtDesc(user);

        List<Product> productList = new ArrayList<>();
        for (WishList wishList : wishLists) {
            productList.add(wishList.getProduct());
        }

        return productList;
    }
}
