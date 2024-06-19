package com.example.firstapp.roles;


import com.example.firstapp.exceptions.api.ApiRequestException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class RoleService {
    private RoleRepository roleRepository ;

    @Autowired
    public RoleService(RoleRepository roleRepository){
         this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }
    public Role addRole(Role role){
        return roleRepository.save(role);
    }

    public void deleteRole(Long roleId){
        System.out.println("deleteRole endpoint => " + roleId);
        boolean exists = roleRepository.existsById(roleId);

        if (!exists) {
            throw  new ApiRequestException("Role with id " + roleId + " does not exist");
        }

         roleRepository.deleteById(roleId);
    }


    @Transactional
    public Role updateRole(Long roleId, String name){
        //check if name is provided & if name is already present
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new ApiRequestException("Role does not exist!"));
        if (!name.isEmpty() || !Objects.equals(name,role.getName())){
            role.setName(name);
        }
        return role;
    }


    public Role getRole(Long roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(()-> new ApiRequestException("Role with id " + roleId + " does not exist"));
        return role;
    }





}
