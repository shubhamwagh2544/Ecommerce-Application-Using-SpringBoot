package com.app.ecommerce.repository;

import com.app.ecommerce.model.AuthenticationToken;
import com.app.ecommerce.model.User;
import com.app.ecommerce.model_request.UserRequest;
import com.app.ecommerce.model_request.UserSignInRequest;

import java.util.List;

public interface UserDao {
    boolean existsUserByEmail(String email);
    String signUpUser(UserRequest userRequest);
    AuthenticationToken signInUser(UserSignInRequest signInRequest);
    List<User> getAllUsers();
    User getUser(Long userId);
    User getUserByEmail(String email);
    String updateUser(Long userId, UserRequest userRequest);
    String deleteUser(Long userId);
}
