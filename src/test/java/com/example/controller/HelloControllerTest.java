package com.example.controller;

import com.example.controller.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HelloController.class)
public class HelloControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetHelloEndpoint() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/");

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Hello from Spring!"));
    }

    @Test
    public void testPostEndpoint() throws Exception {
        this.mockMvc.perform(post("/"))
                .andExpect(status().isAccepted())
                .andExpect(content().string("Goodbye from Spring!"));
    }

}