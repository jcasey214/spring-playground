package com.example.controller;

import com.example.controller.MathController;
import com.example.service.MathService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({MathController.class, MathService.class})
@AutoConfigureMockMvc(secure=false)
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
    public void testCalculateEndpointAddition() throws Exception {
        this.mockMvc.perform(get("/math/calculate?operation=add&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 6 = 10"));
    }

    @Test
    public void testCalculateEndpointSubtraction() throws Exception {
        this.mockMvc.perform(get("/math/calculate?operation=subtract&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("4 - 6 = -2"));
    }

    @Test
    public void testCalculateEndpointMultiplication() throws Exception {
        this.mockMvc.perform(get("/math/calculate?operation=multiply&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("4 * 6 = 24"));
    }

    @Test
    public void testCalculateEndpointDivision() throws Exception {
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

    @Test
    public void testVolumeEndpointPost() throws Exception {
        this.mockMvc.perform(post("/math/volume/3/4/5"))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));
    }

    @Test
    public void testVolumeEndpointPatch() throws Exception {
        this.mockMvc.perform(patch("/math/volume/6/7/8"))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 6x7x8 rectangle is 336"));
    }

    @Test
    public void testAreaCircle() throws Exception {
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "circle")
                .param("radius", "4");
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a circle with a radius of 4 is 50.26548"));
    }

    @Test
    public void testAreaRectangle() throws Exception {
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "rectangle")
                .param("width", "4")
                .param("height", "7");
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a 4x7 rectangle is 28"));
    }

    @Test
    public void testArea_returnsInvalidForInvalidData() throws Exception {
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .accept(MediaType.ALL)
                .param("type", "rectangle")
                .param("radius", "4");
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Invalid"));
    }
}