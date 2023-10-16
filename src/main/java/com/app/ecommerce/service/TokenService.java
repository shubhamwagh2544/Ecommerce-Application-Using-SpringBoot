package com.app.ecommerce.service;

import com.app.ecommerce.model.AuthenticationToken;
import com.app.ecommerce.model.User;
import com.app.ecommerce.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    public void saveAuthenticationToken(AuthenticationToken authenticationToken) {
        this.tokenRepository.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
        return tokenRepository.findByUser(user);
    }
}
