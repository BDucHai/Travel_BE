package com.travel.service;

import com.travel.dto.LoginRequest;
import com.travel.dto.LoginResponse;
import com.travel.dto.CreateUserRequest;
import com.travel.dto.UserProfileResponse;
import com.travel.entity.Role;
import com.travel.entity.User;
import com.travel.repository.RoleRepository;
import com.travel.repository.UserRepository;
import com.travel.security.JwtService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;   
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            RoleRepository roleRepository,       
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;     
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!"ACTIVE".equalsIgnoreCase(user.getStatus())) {
            throw new RuntimeException("User account is not active");
        }

        boolean passwordMatches = passwordEncoder.matches(
                request.getPassword(),
                user.getPasswordHash()
        );

        if (!passwordMatches) {
            throw new RuntimeException("Invalid username or password");
        }

        String token = jwtService.generateToken(user);

        List<String> roles = user.getRoles()
                .stream()
                .map(Role::getName)
                .toList();

        return new LoginResponse(
                token,
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                roles
        );
    }

    public UserProfileResponse createUser(CreateUserRequest request) {

        Role role = roleRepository.findById(
                request.getRoleId() != null ? request.getRoleId() : 1L
        ).orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAvatarUrl(request.getAvatarUrl());

        // mặc định ACTIVE nếu FE không gửi
        user.setStatus(
                request.getStatus() != null
                        ? request.getStatus()
                        : "ACTIVE"
        );

        user.setRoles(Set.of(role));

        User savedUser = userRepository.save(user);

        return new UserProfileResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getFullName(),
                savedUser.getEmail(),
                savedUser.getPhone(),
                savedUser.getAvatarUrl(),
                savedUser.getStatus(),
                savedUser.getRoles()
                        .stream()
                        .map(Role::getName)
                        .toList()
        );
        }
}