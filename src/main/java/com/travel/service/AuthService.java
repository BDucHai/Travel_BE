package com.travel.service;

import com.travel.dto.LoginRequest;
import com.travel.dto.LoginResponse;
import com.travel.entity.Role;
import com.travel.entity.User;
import com.travel.repository.UserRepository;
import com.travel.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

     public User createUser(String username, String rawPassword, String fullName) {
        String hashedPassword = passwordEncoder.encode(rawPassword);

        // Lấy role ADMIN mặc định
        Role adminRole = roleRepository.findByName("ADMIN")
                .orElseThrow(() -> new RuntimeException("Role ADMIN not found"));

        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(hashedPassword);
        user.setFullName(fullName);
        user.setStatus("ACTIVE");
        user.setRoles(List.of(adminRole));

        return userRepository.save(user);
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
}
