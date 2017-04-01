package com.example;

import com.example.service.MathService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({MathController.class, MathService.class})
public class MathControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void testPiEndpoint() throws Exception {
        this.mockMvc.perform(get("/math/pi"))
                .andExpect(status().isOk())
                .andExpect(content().string("3.141592653589793"));
    }

    @Test
    public void testCalculateEndpointAddition() throws Exception{
        this.mockMvc.perform(get("/math/calculate?operation=add&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 6 = 10"));
    }

    @Test
    public void testCalculateEndpointSubtraction() throws Exception{
        this.mockMvc.perform(get("/math/calculate?operation=subtract&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("4 - 6 = -2"));
    }

    @Test
    public void testCalculateEndpointMultiplication() throws Exception{
        this.mockMvc.perform(get("/math/calculate?operation=multiply&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("4 * 6 = 24"));
    }

    @Test
    public void testCalculateEndpointDivision() throws Exception{
        this.mockMvc.perform(get("/math/calculate?operation=divide&x=30&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("30 / 6 = 5"));
    }

    @Test
    public void testSumEndpoint() throws Exception {
        this.mockMvc.perform(post("/math/sum?n=6&n=7&n=9&n=50"))
                .andExpect(status().isOk())
                .andExpect(content().string("6 + 7 + 9 + 50 = 72"));
    }
}