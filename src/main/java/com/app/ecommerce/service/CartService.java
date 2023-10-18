package com.app.ecommerce.service;

import com.app.ecommerce.exception.ResourceDuplicationException;
import com.app.ecommerce.model.Cart;
import com.app.ecommerce.model.Product;
import com.app.ecommerce.model.User;
import com.app.ecommerce.model_request.CartRequest;
import com.app.ecommerce.repository.CartRepository;
import com.app.ecommerce.utility.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ProductService productService;

    public boolean checkIfProductAlreadyPresentInCart(Product product) {
        return cartRepository.existsByProduct(product);
    }

    public String addItemToCart(CartRequest cartRequest, String token) {
        //authenticate token
        tokenService.authenticate(token);

        //get user
        User user = tokenService.getUser(token);

        //get product
        Product product = productService.getProduct(cartRequest.getProductId());

        //check if product exists
        if (checkIfProductAlreadyPresentInCart(product)) {
            throw new ResourceDuplicationException("Product already present in the cart.");
        }

        Cart cart = new Cart(
                product,
                user,
                cartRequest.getQuantity()
        );
        //save cart
        cartRepository.save(cart);
        return "Item added to cart successfully";
    }

    public List<CartResponse> getCartForUser(String token) {
        //authenticate token
        tokenService.authenticate(token);

        //get user
        User user = tokenService.getUser(token);

        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedAtDesc(user);

        List<CartResponse> cartResponseList = new ArrayList<>();
        for (Cart cart : cartList) {
            double totalCost = 0;
            totalCost = cart.getQuantity() * cart.getProduct().getPrice();
            CartResponse response = CartResponse
                    .builder()
                    .id(cart.getCartId())
                    .createdAt(cart.getCreatedAt())
                    .product(cart.getProduct())
                    .quantity(cart.getQuantity())
                    .totalCost(totalCost)
                    .build();

            cartResponseList.add(response);
        }

        return cartResponseList;
    }

    public String deleteCartForUser(String token) {
        //authenticate
        tokenService.authenticate(token);

        //get user
        User user = tokenService.getUser(token);

        //delete
        cartRepository.deleteAllByUser(user);

        return "Cart deleted successfully";
    }
}
