package com.app.ecommerce.controller;

import com.app.ecommerce.model.AuthenticationToken;
import com.app.ecommerce.utility.SignInResponse;
import com.app.ecommerce.model_request.UserRequest;
import com.app.ecommerce.model_request.UserSignInRequest;
import com.app.ecommerce.service.UserService;
import com.app.ecommerce.utility.ApiResponse;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/auth")
@Api(tags = "Authentication-Controller")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<ApiResponse> signUpUser(@RequestBody UserRequest userRequest) {
        String message = userService.signUpUser(userRequest);
        return new ResponseEntity<>(
                new ApiResponse(true, message, LocalDateTime.now()), HttpStatus.CREATED
        );
    }

    @PostMapping("/signIn")
    public ResponseEntity<SignInResponse> signInUser(@RequestBody UserSignInRequest signInRequest) {
        AuthenticationToken authToken = userService.signInUser(signInRequest);
        return new ResponseEntity<>(
                new SignInResponse(true, "User signed in successfully", authToken.getToken()),
                HttpStatus.OK
        );
    }
}
