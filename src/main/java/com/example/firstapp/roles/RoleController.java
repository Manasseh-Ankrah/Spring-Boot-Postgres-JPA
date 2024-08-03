package com.example.firstapp.roles;


import com.example.firstapp.dto.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/roles")
public class RoleController {
    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<Role> fetchAllRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping
    public ResponseEntity<CustomResponse> saveRole(@Valid @RequestBody Role role) throws URISyntaxException {
       Role result = roleService.addRole(role);
        CustomResponse response = new CustomResponse(result,"Role added successfully!");
        URI location = new URI("/api/v1/roles/" + result.getId());
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping(path = "{roleId}")
    public ResponseEntity<CustomResponse> updateRole(@PathVariable(required = true) Long roleId, @RequestParam(required = true) String name ) throws URISyntaxException{
       Role result = roleService.updateRole(roleId,name);
       CustomResponse response = new CustomResponse<>(result,"Role updated successfully!");
       URI location = new URI("/api/v1/roles" + result.getId());
        return ResponseEntity.created(location).body(response);
    }


    @DeleteMapping(path = "{roleId}")
    public ResponseEntity<CustomResponse> deleteRole(@PathVariable(required = true) Long roleId) throws URISyntaxException {
       roleService.deleteRole(roleId);
        CustomResponse response = new CustomResponse<>("Role deleted successfully!");
        URI location = new URI("/api/v1/roles/");
        return ResponseEntity.created(location).body(response);
    }


    @GetMapping(path = "{roleId}")
    public ResponseEntity<CustomResponse> fetchRole(@PathVariable(required = true) Long roleId) throws URISyntaxException{
        Role result = roleService.getRole(roleId);
        CustomResponse response = new CustomResponse<>(result,"Single role retrieved successfully!");
        URI location = new URI("/api/v1/roles" + result.getId());
        return ResponseEntity.created(location).body(response);
    }
}
