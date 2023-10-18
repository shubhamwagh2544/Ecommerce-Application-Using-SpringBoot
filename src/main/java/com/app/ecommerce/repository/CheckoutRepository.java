package com.app.ecommerce.repository;

import com.app.ecommerce.model.Cart;
import com.app.ecommerce.model.Checkout;
import com.app.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {
    List<Checkout> findAllByUser(User user);
    boolean existsByCart(Cart cart);
}
