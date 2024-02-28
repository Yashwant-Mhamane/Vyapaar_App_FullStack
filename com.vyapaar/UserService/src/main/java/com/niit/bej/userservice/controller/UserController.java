package com.niit.bej.userservice.controller;


import com.niit.bej.userservice.domain.User;
import com.niit.bej.userservice.exception.UserAlreadyExistException;
import com.niit.bej.userservice.exception.UserNotFoundException;
import com.niit.bej.userservice.security.SecurityTokenGenerator;
import com.niit.bej.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/userauth")
public class UserController {

    private final UserService userService;
    private final SecurityTokenGenerator securityTokenGenerator;

    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User userDetails = userService.registerUser(user);
            return new ResponseEntity<>(userDetails, HttpStatus.CREATED);
        } catch (UserAlreadyExistException userAlreadyExistException) {
            return new ResponseEntity<>("User Already Exists", HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            User userDetails = userService.login(user.getEmailId(), user.getPassword());
            if (userDetails != null) {
                Map<String, String> generatedToken = securityTokenGenerator.generateToken(user);
                return new ResponseEntity<>(generatedToken, HttpStatus.OK);
            } else {
                throw new UserNotFoundException("User Not Found Exception");
            }
        } catch (UserNotFoundException userNotFoundException) {

            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/sender/{emailId}")
    public ResponseEntity<?> mailSender(@PathVariable String emailId ){
        try {
            userService.mailSender(emailId);
            return new ResponseEntity<>( HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found.",HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/forget/{password}")
    public ResponseEntity<?> forgetPassword(@RequestBody User user, @PathVariable String password){
        try {
            return new ResponseEntity<>(userService.forgetPassword(user,password), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("OTP wrong.",HttpStatus.NOT_FOUND);

        }
    }
}

