package com.example.firstapp.auth;

import com.example.firstapp.exceptions.api.ApiRequestException;
import com.example.firstapp.roles.Role;
import com.example.firstapp.roles.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthService {
    private AuthRepository authRepository;
    private RoleRepository roleRepository;

    private HttpStatus badRequest = HttpStatus.BAD_REQUEST;
    private HttpStatus conflictRequest = HttpStatus.CONFLICT;


    @Autowired
    public AuthService(AuthRepository authRepository,RoleRepository roleRepository) {
        this.authRepository = authRepository;
        this.roleRepository = roleRepository;
    }

    public List<Auth> getUsers() {
        return authRepository.findAll();
    }

    public Auth registerUser(Auth payload) {

//        List<String> roles = List.of("admin","user");

        Optional<Auth> userOptional = authRepository.findUserByEmail(payload.getEmail());
        Integer roleId = Integer.parseInt(payload.roleId);

        System.out.println("ROLE ID " + roleId);
        Long longValue = (long) roleId;

        Role role = roleRepository.findById(longValue).orElseThrow(()-> new ApiRequestException("Role id " + longValue + " does not exist"));


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

        // Save record
        payload.getRole().add(role);
        return authRepository.save(payload);
    }


    public Auth getUser(Long roleId) {
        Auth auth = authRepository.findById(roleId).orElseThrow(()-> new ApiRequestException("User with id " + roleId + " does not exist"));
        return auth;
    }

    public void addRoleToUser(String username, Long roleId) {

         Auth user = authRepository.findByUsername(username);
        if(user == null) {
            throw new ApiRequestException("Username does not exist");
        }

        System.out.println(user);
        Role role = roleRepository.findById(roleId).orElseThrow(()-> new ApiRequestException("Role with id " + roleId + " does not exist!"));
        System.out.println("Role =>" + role);

        user.getRole().add(role);

        authRepository.save(user);
    }
}
