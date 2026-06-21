package com.travel.controller;

import com.travel.dto.LoginRequest;
import com.travel.dto.LoginResponse;
import com.travel.dto.CreateUserRequest;
import com.travel.dto.UserProfileResponse;
import com.travel.service.AuthService;
import com.travel.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/create")
    public UserProfileResponse createUser(@RequestBody CreateUserRequest request) {
        return authService.createUser(request);
    }

    @GetMapping("/active-customers")
    public List<UserProfileResponse> getAllActiveUsersWithUserRole() {
        return userService.getAllActiveUsersWithUserRole();
    }

    @GetMapping("/user")
    public List<UserProfileResponse> getAllUsers() {
        return userService.getAllUsers();
    }
}
