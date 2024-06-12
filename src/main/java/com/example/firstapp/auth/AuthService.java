package com.example.firstapp.auth;

import com.example.firstapp.exceptions.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        System.out.println(payload);

        System.out.println(" Contains Role => " + roles.contains(payload.getRole()));



        // Validate all entries
        if (payload.getName() == null || payload.getEmail() == null || payload.getPhone() == null || payload.getPassword() == null || payload.getConfirmPassword() == null|| payload.getRole() == null) {
            throw  new ApiRequestException("All fields are required!", badRequest.value(), badRequest);
        }
        Optional<Auth> userOptional = authRepository.findUserByEmail(payload.getEmail());


        // Verify and validate email
        if (!payload.getEmail().contains("@gmail.com")) {
            throw  new ApiRequestException("A valid email address is required!", badRequest.value(), badRequest);
        }

        if (userOptional.isPresent()) {
            throw  new ApiRequestException("Email is already taken!", conflictRequest.value(), conflictRequest);
        }

        // Verify password
        if (payload.getPassword().length() < 8 ) {
            throw  new ApiRequestException("Password length should not be less than 8", badRequest.value(), badRequest);
        }

        if (!Objects.equals(payload.getPassword(),payload.getConfirmPassword())) {
            throw  new ApiRequestException("Enter the same password to confirm!", badRequest.value(), badRequest);
        }

        // Verify User Role
        if (!roles.contains(payload.getRole())) {
            throw  new ApiRequestException("User role does not exist!", badRequest.value(), badRequest);
        }

        // Save record
        return authRepository.save(payload);
    }
}
