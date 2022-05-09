package com.example.restaurantbillingsystem.web;

import org.springframework.core.convert.converter.Converter;

import com.example.restaurantbillingsystem.domain.Order.Status;

public class StringToStatusConverter implements Converter<String, Status> {

    @Override
    public Status convert(String source) {
        return Status.valueOf(source.toUpperCase());
    }
}
