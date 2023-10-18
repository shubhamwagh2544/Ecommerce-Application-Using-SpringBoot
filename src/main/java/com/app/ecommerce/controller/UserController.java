package com.app.ecommerce.controller;

import com.app.ecommerce.model.User;
import com.app.ecommerce.model_request.UserRequest;
import com.app.ecommerce.service.UserService;
import com.app.ecommerce.utility.ApiResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@Api(tags = "User-Controller")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(
                this.userService.getAllUsers(), HttpStatus.OK
        );
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable long userId) {
        return new ResponseEntity<>(
                this.userService.getUser(userId), HttpStatus.OK
        );
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable long userId, UserRequest userRequest) {
        String message = this.userService.updateUser(userId, userRequest);
        return new ResponseEntity<>(
                new ApiResponse(true, message, LocalDateTime.now()), HttpStatus.OK
        );
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable long userId) {
        String message = this.userService.deleteUser(userId);
        return new ResponseEntity<>(
                new ApiResponse(true, message, LocalDateTime.now()), HttpStatus.OK
        );
    }
}
