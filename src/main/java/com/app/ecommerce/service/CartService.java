package com.app.ecommerce.service;

import com.app.ecommerce.exception.AuthenticationFailedException;
import com.app.ecommerce.exception.ResourceDuplicationException;
import com.app.ecommerce.exception.ResourceNotFoundException;
import com.app.ecommerce.model.Cart;
import com.app.ecommerce.model.Product;
import com.app.ecommerce.model.User;
import com.app.ecommerce.model_request.CartRequest;
import com.app.ecommerce.model_request.CartUpdateRequest;
import com.app.ecommerce.repository.CartRepository;
import com.app.ecommerce.utility.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
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

    public boolean checkIfCartExists(long cartId) {
        return cartRepository.existsByCartId(cartId);
    }

    public Cart getCartByCartId(long cartId, String token) {
        //authenticate
        tokenService.authenticate(token);

        //get user
        User user = tokenService.getUser(token);

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Cart with id %s not found", cartId)
                ));

        if (cart.getUser().getUserId() != user.getUserId()) {
            throw new AuthenticationFailedException("Cart does not belong to user");
        }

        return cart;
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

    public String updateCart(CartUpdateRequest updateRequest, String token) {
        //authenticate token
        tokenService.authenticate(token);

        //get user
        User user = tokenService.getUser(token);

        //check if product exists
        Product product = productService.getProduct(updateRequest.getProductId());
        if (Objects.isNull(product)) {
            throw new ResourceNotFoundException("Product does not present in cart");
        }

        Cart cart = cartRepository.findByProduct(product);
        cart.setUser(user);
        cart.setProduct(product);
        cart.setQuantity(updateRequest.getQuantity());
        cart.setCreatedAt(updateRequest.getCreatedAt());

        return "Cart updated successfully";
    }
}
