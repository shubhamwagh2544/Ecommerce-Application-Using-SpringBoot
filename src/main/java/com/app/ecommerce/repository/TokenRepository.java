package com.app.ecommerce.repository;

import com.app.ecommerce.model.AuthenticationToken;
import com.app.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Long> {

    AuthenticationToken findByUser(User user);

    AuthenticationToken findByToken(String token);

}
