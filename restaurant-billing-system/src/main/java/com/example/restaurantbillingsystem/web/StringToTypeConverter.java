package com.example.restaurantbillingsystem.web;

import org.springframework.core.convert.converter.Converter;

import com.example.restaurantbillingsystem.Menu.Type;

public class StringToTypeConverter implements Converter<String, Type> {

    @Override
    public Type convert(String source) {
        return Type.valueOf(source.toUpperCase());
    }

}
