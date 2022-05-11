package com.example.restaurantbillingsystem.web;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

import com.example.restaurantbillingsystem.domain.Order;
import com.example.restaurantbillingsystem.domain.Order.Status;

class StringToStatusConverterTest {

    @Test
    void provideConvert_shouldReturnOrderStatus_whenInputUpperCaseString() {
        Order.Status expected = Status.OPEN;
        StringToStatusConverter stringToStatusConverter = new StringToStatusConverter();
        Order.Status actual = stringToStatusConverter.convert("OPEN");
        
        assertThat(actual, equalTo(expected));
    }
    
    @Test
    void provideConvert_shouldReturnOrderStatus_whenInputLowerCaseString() {
        Order.Status expected = Status.OPEN;
        StringToStatusConverter stringToStatusConverter = new StringToStatusConverter();
        Order.Status actual = stringToStatusConverter.convert("open");
        
        assertThat(actual, equalTo(expected));
    }

}
