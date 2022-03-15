package com.example.restaurantbillingsystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.restaurantbillingsystem.web.StringToTypeConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }
    
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToTypeConverter());
    }
    
}
