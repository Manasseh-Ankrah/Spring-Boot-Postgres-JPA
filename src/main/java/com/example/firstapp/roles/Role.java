package com.example.firstapp.roles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Table
@Entity(name = "role")
public class Role {

    @Id
    @SequenceGenerator(name = "role_sequence",sequenceName = "role_sequence",allocationSize = 1)
    @GeneratedValue(generator = "role_sequence",strategy = GenerationType.IDENTITY)

    @JsonIgnore
    private Long id;

    @NotEmpty(message = "Name is a required field!")
    private String name;

    public Role() {
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
