package com.travel.controller;

import com.travel.dto.CreateAdminRequest;
import com.travel.entity.Role;
import com.travel.entity.User;
import com.travel.repository.RoleRepository;
import com.travel.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/setup")
@CrossOrigin(origins = "*")
public class SetupController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public SetupController(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/admin")
    public String createAdmin(@RequestBody CreateAdminRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return "Username already exists";
        }

        if (request.getEmail() != null && userRepository.existsByEmail(request.getEmail())) {
            return "Email already exists";
        }

        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("ROLE_ADMIN not found"));

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setStatus("ACTIVE");

        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "Create admin successfully";
    }
}