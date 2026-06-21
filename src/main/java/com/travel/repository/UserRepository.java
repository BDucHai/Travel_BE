package com.travel.repository;

import com.travel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

     @Query("""
        SELECT DISTINCT u
        FROM User u
        JOIN u.roles r
        WHERE u.status = 'ACTIVE'
          AND r.name = 'USER'
    """)
    List<User> findAllActiveUsersWithUserRole();
}