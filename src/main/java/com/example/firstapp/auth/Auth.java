package com.example.firstapp.auth;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Email;

@Entity
@Table
public class Auth {
    @Id
    @SequenceGenerator(
            name = "auth_sequence",
            sequenceName = "auth_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "auth_sequence",
            strategy = GenerationType.SEQUENCE
    )

    @JsonIgnore
    Long id;

    @NotBlank(message = "Username is a required field!")
    String username;

    @NotBlank
    @Email
//    @Email(message = "Email is a required field!")
    String email;

    @NotBlank(message = "Phone is a required field!")
    String phone;

    @NotBlank(message = "Password is a required field!")
    String password;
    @Transient
    @NotBlank(message = "Confirm Password is a required field!")
    String confirmPassword;

    @NotBlank(message = "Role is a required field!")
    String role;

    // Constructors
    public Auth(){}
    public Auth(Long id, String username, String email, String phone, String password, String confirmPassword, String role){
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.role = role;
    }
    public Auth(String username, String email, String phone, String password, String confirmPassword, String role){
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.role = role;
    }

    // Getters and Setters

    public void setId(Long id) {
        this.id = id;
    }
     public Long getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

//    public String getConfirmPassword() {
//        return confirmPassword;
//    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
//                ", confirmPassword='" + confirmPassword  +
                ", role='" + role +
                '}';
    }
}
