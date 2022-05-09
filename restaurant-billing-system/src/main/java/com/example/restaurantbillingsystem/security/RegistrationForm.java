package com.example.restaurantbillingsystem.security;

import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.restaurantbillingsystem.domain.User;

public class RegistrationForm {
    
    @Size(min = 2, message = "Name must be at least 2 characters long")
    private String name;
    @Email(message="Please provide a valid email address")
    private String email;
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    
    public RegistrationForm() {
        
    }

    public RegistrationForm(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(null, name, email, passwordEncoder.encode(password));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, password);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RegistrationForm other = (RegistrationForm) obj;
        return Objects.equals(email, other.email) && Objects.equals(name, other.name)
                && Objects.equals(password, other.password);
    }

    @Override
    public String toString() {
        return "RegistrationForm [name=" + name + ", email=" + email + ", password=" + password + "]";
    }
    
}
