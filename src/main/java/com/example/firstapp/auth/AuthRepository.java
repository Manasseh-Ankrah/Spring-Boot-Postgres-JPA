package com.example.firstapp.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth,Long> {
    Optional<Auth> findUserByEmail(String email);
    Auth findByUsername(String username);
}
