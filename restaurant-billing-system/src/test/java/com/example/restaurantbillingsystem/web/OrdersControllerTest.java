package com.example.restaurantbillingsystem.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class OrdersControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username="user",roles={"USER"})
    void givenUserPageURI_whenMockMVC_thenReturnsOrdersViewName() throws Exception {
        this.mockMvc.perform(get("/orders")).andDo(print()).andExpect(status().isOk()).andExpect(view().name("userPages/orders"));
    }
    
    @Test
    @WithMockUser(username="user",roles={"USER"})
    void givenUserPageURI_whenMockMVC_thenReturnsOrderItemsViewName() throws Exception {
        this.mockMvc.perform(get("/orders/order-items/1")).andDo(print()).andExpect(status().isOk()).andExpect(view().name("userPages/orderitems"));
    }

}
