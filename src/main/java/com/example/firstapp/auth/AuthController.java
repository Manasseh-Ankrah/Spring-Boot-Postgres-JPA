package com.example.firstapp.auth;


import com.example.firstapp.dto.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CustomResponse> registerUser(@RequestBody Auth payload) throws URISyntaxException {

        Auth result = authService.registerUser(payload);
        CustomResponse response = new CustomResponse(result,"Registration Successful!");
        URI location = new URI("/api/v1/auth/register/" + result.getId());
        return ResponseEntity.created(location).body(response);
    }

}


