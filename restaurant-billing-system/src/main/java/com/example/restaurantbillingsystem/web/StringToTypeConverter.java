package com.example.restaurantbillingsystem.web;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.restaurantbillingsystem.domain.Menu.Type;

@Component
public class StringToTypeConverter implements Converter<String, Type> {

    @Override
    public Type convert(String source) {
        return Type.valueOf(source.toUpperCase());
    }

}
