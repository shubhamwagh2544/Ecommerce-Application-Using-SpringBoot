package com.app.ecommerce.repository;

import com.app.ecommerce.model.Product;
import com.app.ecommerce.model.User;
import com.app.ecommerce.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
    boolean existsByProduct(Product product);
    List<WishList> findAllByUserOrderByCreatedAtDesc(User user);
}
