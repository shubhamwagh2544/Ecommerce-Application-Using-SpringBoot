package com.app.ecommerce.repository;

import com.app.ecommerce.exception.AuthenticationFailedException;
import com.app.ecommerce.exception.ResourceDuplicationException;
import com.app.ecommerce.exception.ResourceNotFoundException;
import com.app.ecommerce.model.AuthenticationToken;
import com.app.ecommerce.model.User;
import com.app.ecommerce.model_request.UserRequest;
import com.app.ecommerce.model_request.UserSignInRequest;
import com.app.ecommerce.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

import static com.app.ecommerce.utility.HashPassword.hashPasswordWithMD5;

@Repository("user_jpa")
public class UserDaoImplementation implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean existsUserByEmail(String email) {
        return getAllUsers().stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }

    @Override
    @Transactional
    public String signUpUser(UserRequest userRequest) {
        //check if user exists
        if (!existsUserByEmail(userRequest.getEmail())) {
            //encrypt the password first to save user
            String encryptedPassword = "";
            try {
                encryptedPassword = hashPasswordWithMD5(userRequest.getPassword());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            User user = new User(
                    userRequest.getFirstName(),
                    userRequest.getLastName(),
                    userRequest.getEmail(),
                    encryptedPassword
            );

            userRepository.save(user);

            //create token
            AuthenticationToken authenticationToken = new AuthenticationToken(user);

            //save token
            tokenService.saveAuthenticationToken(authenticationToken);

            return "User registered successfully";
        }

        else throw new ResourceDuplicationException(
                String.format("User with email %s already exists..!", userRequest.getEmail())
        );
    }

    @Override
    public AuthenticationToken signInUser(UserSignInRequest signInRequest) {
        //check if user exists
        if (!existsUserByEmail(signInRequest.getEmail())) {
            throw new AuthenticationFailedException(
                    String.format("User with email %s is not registered yet.. !", signInRequest.getEmail())
            );
        }

        //retrieve the user
        User user = getUserByEmail(signInRequest.getEmail());

        //check if password matches
        String password = signInRequest.getPassword();
        try {
            if (!user.getPassword().equals(hashPasswordWithMD5(password))) {
                throw new AuthenticationFailedException("Invalid Password.. Try Again");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        AuthenticationToken authenticationToken = tokenService.getToken(user);

        if (Objects.isNull(authenticationToken)) {
            throw new AuthenticationFailedException("Invalid Authentication Token");
        }
        return authenticationToken;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long userId) {
        return getAllUsers().stream()
                .filter(user -> user.getUserId() == userId)
                .findAny()
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format("User with id %s not found", userId)
                        )
                );
    }

    @Override
    public User getUserByEmail(String email) {
        return getAllUsers().stream()
                .filter(user -> user.getEmail().equals(email))
                .findAny()
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format("User with email %s not found", email)
                        )
                );
    }

    @Override
    public String updateUser(Long userId, UserRequest userRequest) {
        return null;
    }

    @Override
    public String deleteUser(Long userId) {
        return null;
    }

}
