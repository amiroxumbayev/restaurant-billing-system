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
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void givenAdminPageURI_whenMockMVC_thenReturnsAdminViewName() throws Exception {
        this.mockMvc.perform(get("/admin")).andDo(print()).andExpect(status().isOk()).andExpect(view().name("adminPages/admin"));
    }
    
    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void givenMenuEditorPageURI_whenMockMVC_thenReturnsMenuEditorViewName() throws Exception {
        this.mockMvc.perform(get("/admin/menueditor")).andDo(print()).andExpect(status().isOk()).andExpect(view().name("adminPages/menueditor"));
    }
    
    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void givenAddMenuPageURI_whenMockMVC_thenReturnsAddMenuViewName() throws Exception {
        this.mockMvc.perform(get("/admin/menueditor/addmenu")).andDo(print()).andExpect(status().isOk()).andExpect(view().name("adminPages/addmenu"));
    }

}
