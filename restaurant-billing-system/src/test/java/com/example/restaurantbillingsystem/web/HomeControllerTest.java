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
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void givenHomePageURI_whenMockMVC_thenReturnsHomePageViewName() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(view().name("home"));
    }
    
    @Test
    void givenAdminPageURI_whenMockMVC_thenReturnsRedirect() throws Exception {
        this.mockMvc.perform(get("/user")).andDo(print()).andExpect(status().is3xxRedirection());
    }

    @Test
    void givenUserPageURI_whenMockMVC_thenReturnsLRedirect() throws Exception {
        this.mockMvc.perform(get("/admin")).andDo(print()).andExpect(status().is3xxRedirection());
    }
    
    @Test
    void givenErrorPageURI_whenMockMVC_thenReturnsRedirect() throws Exception {
        this.mockMvc.perform(get("/403")).andDo(print()).andExpect(status().is3xxRedirection());
    }
    
    @Test
    @WithMockUser(username="user",roles={"USER"})
    void givenUserPageURI_whenMockMVC_thenReturnsHomeViewName() throws Exception {
        this.mockMvc.perform(get("/user")).andDo(print()).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/orders"));
    }
    
    @Test
    void givenLoginPageURI_whenMockMVC_thenReturnsLoginViewName() throws Exception {
        this.mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk()).andExpect(view().name("login"));
    }

}
