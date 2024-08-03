package com.example.firstapp.auth;


import com.example.firstapp.dto.CustomResponse;
import com.example.firstapp.roles.Role;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.net.URI;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/auth/")
public class AuthController {
  private AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping(path = "users")
    public List<Auth> getUsers() {
        return authService.getUsers();
    }

    @PostMapping(path = "register")
    public ResponseEntity<CustomResponse> registerUser(@Valid @RequestBody Auth payload) throws URISyntaxException {
        Auth result = authService.registerUser(payload);
        CustomResponse response = new CustomResponse(result,"Registration Successful!");
        URI location = new URI("/api/v1/auth/register/" + result.getId());
        return ResponseEntity.created(location).body(response);
    }


    @GetMapping(path = "users/{userId}")
    public ResponseEntity<CustomResponse> fetchUser(@PathVariable(required = true) Long userId) throws URISyntaxException{
        Auth result = authService.getUser(userId);
        CustomResponse response = new CustomResponse<>(result,"Single user retrieved successfully!");
        URI location = new URI("/api/v1/roles" + result.getId());
        return ResponseEntity.created(location).body(response);
    }


    @PostMapping(path = "/add-user-role/{username}/{roleId}")
    public void addRoleToUser(@PathVariable(required = true) String username, @PathVariable(required = true) Long roleId) {
        authService.addRoleToUser(username, roleId);
    }

}


