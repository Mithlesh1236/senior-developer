package com.kumar.architect.senior.developer.controller;

import com.kumar.architect.senior.developer.service.User;
import com.kumar.architect.senior.developer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        // Additional authentication logic can be added here
        // For simplicity, let's assume the login is successful
        return new ResponseEntity<>("Login successful", HttpStatus.OK);
    }
    @Autowired
    private UserService userService;

    // ... other methods ...

    @PostMapping("/login")

        String username = credentials.get("username");
        String password = credentials.get("password");

        // Additional authentication logic can be added here
        UserDetails userDetails = userService.loadUserByUsername(username);

        // Assuming a basic password comparison
        String passwordEncoder = "null";
        if (passwordEncoder.matches(password)) {
            // Generate an authentication token (JWT token in a real-world scenario)
            String authToken = "your_generated_token_here";

            // Return the token in the response
            return new ResponseEntity<>(authToken, HttpStatus.OK);
        } else {
            // Invalid credentials
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
}
