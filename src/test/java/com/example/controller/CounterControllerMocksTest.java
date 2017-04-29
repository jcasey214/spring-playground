package com.example.controller;

import com.example.service.WordCounter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CounterController.class)
public class CounterControllerMocksTest {

    @MockBean
    WordCounter wordCounter;

    @Autowired
    MockMvc mockMvc;

    @Before
    public void setup() {
        Map<String, Integer> mockResult = new HashMap<>();
        mockResult.put("so", 1);
        mockResult.put("much", 1);
        mockResult.put("time", 2);

        when(wordCounter.count(anyString())).thenReturn(mockResult);

    }

    @Test
    public void testPiEndpoint() throws Exception {

        MockHttpServletRequestBuilder request = post("/words/count")
                .contentType(MediaType.TEXT_PLAIN)
                .accept(MediaType.APPLICATION_JSON)
                .content("so much time");

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.so", equalTo(1)))
                .andExpect(jsonPath("$.much", equalTo(1)))
                .andExpect(jsonPath("$.time", equalTo(2)));
    }


}