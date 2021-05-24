package com.gsbraga.apicommerce.controllers;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ProductControllerTest {

    public WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @Before("")
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void testRequestSuccess() throws Exception{
        String url = "/products/7";
        this.mvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", equalTo("TV")));
    }

//    @Test
//    public void testRequestError() throws Exception{
//        String url = "/v1/products/5";
//        this.mvc.perform(get(url))
//                .andExpect(status().is5xxServerError());
//    }
}
