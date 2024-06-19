package com.example.firstapp.auth;

import com.example.firstapp.exceptions.api.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthService {
    private AuthRepository authRepository;
    private HttpStatus badRequest = HttpStatus.BAD_REQUEST;
    private HttpStatus conflictRequest = HttpStatus.CONFLICT;


    @Autowired
    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public List<Auth> getUsers() {
        return authRepository.findAll();
    }

    public Auth registerUser(Auth payload) {

        List<String> roles = List.of("admin","user");

        Optional<Auth> userOptional = authRepository.findUserByEmail(payload.getEmail());


        // Verify and validate email
        if (!payload.getEmail().contains("@gmail.com")) {
            throw  new ApiRequestException("A valid email address is required!");
        }

        if (userOptional.isPresent()) {
            throw  new ApiRequestException("Email is already taken!");
        }

        // Verify password
        if (payload.getPassword().length() < 8 ) {
            throw  new ApiRequestException("Password length should not be less than 8");
        }

        if (!Objects.equals(payload.getPassword(), payload.confirmPassword)) {
            throw  new ApiRequestException("Enter the same password to confirm!");
        }

        // Verify User Role
        if (!roles.contains(payload.getRole())) {
            throw  new ApiRequestException("User role does not exist!");
        }

        // Save record
        return authRepository.save(payload);
    }
}
