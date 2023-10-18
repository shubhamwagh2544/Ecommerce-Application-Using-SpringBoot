package com.app.ecommerce.service;

import com.app.ecommerce.exception.ResourceDuplicationException;
import com.app.ecommerce.exception.ResourceNotFoundException;
import com.app.ecommerce.model.Cart;
import com.app.ecommerce.model.Checkout;
import com.app.ecommerce.model.User;
import com.app.ecommerce.repository.CheckoutRepository;
import com.app.ecommerce.utility.CheckoutResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckoutService {

    @Autowired
    private CheckoutRepository checkoutRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private TokenService tokenService;

    public CheckoutResponse addCartToCheckout(long cartId, String token) {
        //authenticate token
        tokenService.authenticate(token);

        //get user
        User user = tokenService.getUser(token);

        //check if cart exists
        boolean ifCartExists = cartService.checkIfCartExists(cartId);
        if (!ifCartExists) {
            throw new ResourceNotFoundException("Cart does not exist for user");
        }

        Cart cart = cartService.getCartByCartId(cartId, token);
        double totalCost = cart.getProduct().getPrice() * cart.getQuantity();

        //check if cart already in checkout
        if (checkoutRepository.existsByCart(cart)) {
            throw new ResourceDuplicationException("Cart already added for checkout");
        }

        Checkout checkout = new Checkout(cart, user);
        checkoutRepository.save(checkout);

        return new CheckoutResponse(totalCost);
    }

    public List<Checkout> getCheckoutForUser(String token) {
        //authenticate
        tokenService.authenticate(token);

        //get user
        User user = tokenService.getUser(token);

        return checkoutRepository.findAllByUser(user);
    }
}
