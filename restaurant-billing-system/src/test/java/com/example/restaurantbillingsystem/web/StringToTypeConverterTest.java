package com.example.restaurantbillingsystem.web;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import com.example.restaurantbillingsystem.domain.Menu;
import com.example.restaurantbillingsystem.domain.Menu.Type;

class StringToTypeConverterTest {

    @Test
    void provideConvert_shouldReturnMenuType_whenInputUpperCaseString() {
        Menu.Type expected = Type.MAIN;
        StringToTypeConverter stringToTypeConverter = new StringToTypeConverter();
        Menu.Type actual = stringToTypeConverter.convert("MAIN");
        
        assertThat(actual, equalTo(expected));
    }
    
    @Test
    void provideConvert_shouldReturnMenuType_whenInputLowerCaseString() {
        Menu.Type expected = Type.MAIN;
        StringToTypeConverter stringToTypeConverter = new StringToTypeConverter();
        Menu.Type actual = stringToTypeConverter.convert("main");
        
        assertThat(actual, equalTo(expected));
    }

}
