package com.app.ecommerce.repository;

import com.app.ecommerce.model.Cart;
import com.app.ecommerce.model.Product;
import com.app.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    boolean existsByCartId(long cartId);
    boolean existsByProduct(Product product);
    Cart findByProduct(Product product);
    List<Cart> findAllByUserOrderByCreatedAtDesc(User user);
    @Transactional
    void deleteAllByUser(User user);

}
