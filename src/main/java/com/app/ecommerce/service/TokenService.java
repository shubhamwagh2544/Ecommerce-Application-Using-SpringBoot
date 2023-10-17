package com.app.ecommerce.service;

import com.app.ecommerce.exception.AuthenticationFailedException;
import com.app.ecommerce.model.AuthenticationToken;
import com.app.ecommerce.model.User;
import com.app.ecommerce.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

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

    public User getUser(String token) {
        AuthenticationToken authToken = tokenRepository.findByToken(token);
        if (Objects.isNull(authToken)) {
            throw new AuthenticationFailedException("Invalid Token");
        }
        return authToken.getUser();
    }
    public void authenticate(String token) {
        if (Objects.isNull(token)) {
            throw new AuthenticationFailedException("Invalid Token..");
        }
        User user = getUser(token);
        if (Objects.isNull(user)) {
            throw new AuthenticationFailedException("Invalid User..");
        }
    }
}
