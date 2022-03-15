package com.example.restaurantbillingsystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;

public class MenuOrder {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Date placedAt;

    @NotBlank(message = "Table number is required")
    private int tableNumber;
    
    @NotNull
    private double totalPrice;

    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;
    
    @NotNull
    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<Menu> menus = new ArrayList<>();
}
