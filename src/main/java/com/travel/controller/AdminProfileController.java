package com.travel.controller;

import com.travel.dto.ChangePasswordRequest;
import com.travel.dto.UserProfileResponse;
import com.travel.dto.CreateUserRequest;
import com.travel.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminProfileController {

    private final UserService userService;

    public AdminProfileController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public UserProfileResponse getCurrentUser(Authentication authentication) {
        String username = authentication.getName();

        return userService.getCurrentUserProfile(username);
    }

    @PutMapping("/change-password")
    public String changePassword(
            Authentication authentication,
            @RequestBody ChangePasswordRequest request
    ) {
        String username = authentication.getName();

        return userService.changePassword(username, request);
    }

    @PostMapping
    public ResponseEntity<UserProfileResponse> createUser(
            @RequestBody CreateUserRequest request
    ) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}