package com.example.restaurantbillingsystem.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main-dining")
public class MainDiningController {
    
    @GetMapping
    public String orders() {
        return "userPages/maindining";
    }
}
