package com.example.restaurantbillingsystem.security;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.restaurantbillingsystem.data.UserRepository;

@Controller
@RequestMapping("/admin/register")
public class RegistrationController {
    
    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    
    @GetMapping
    public String registerForm(Model m) {
        m.addAttribute("user", new RegistrationForm());
        return "adminPages/registration";
    }
    
    @PostMapping("/registration")
    public String processRegistration(@ModelAttribute("user") @Valid RegistrationForm form, Errors errors) {
        
        if (errors.hasErrors()) {
            return "adminPages/registration";
        }
        userRepo.save(form.toUser(passwordEncoder));
        return "redirect:/admin";
    }
}
