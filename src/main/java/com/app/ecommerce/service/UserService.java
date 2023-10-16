package com.app.ecommerce.service;

import com.app.ecommerce.exception.AuthenticationFailedException;
import com.app.ecommerce.exception.ResourceDuplicationException;
import com.app.ecommerce.model.AuthenticationToken;
import com.app.ecommerce.model.User;
import com.app.ecommerce.model_request.UserRequest;
import com.app.ecommerce.model_request.UserSignInRequest;
import com.app.ecommerce.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static com.app.ecommerce.utility.HashPassword.hashPasswordWithMD5;

@Service
public class UserService {

    @Autowired
    @Qualifier("user_jpa")
    private UserDao userDao;

    public boolean existsUserByEmail(String email) {
        return userDao.existsUserByEmail(email);
    }

    public String signUpUser(UserRequest userRequest) {
        return userDao.signUpUser(userRequest);
    }

    public AuthenticationToken signInUser(UserSignInRequest signInRequest) {
        return userDao.signInUser(signInRequest);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getUser(Long userId) {
        return userDao.getUser(userId);
    }

    public String updateUser(Long userId, UserRequest userRequest) {
        return "User updated successfully";
    }

    public String deleteUser(Long userId) {
        return "User deleted successfully";
    }
}
