package com.example.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class MathServiceTest {

    MathService mathService = new MathService();

    @Test
    public void testCalculateHandlesAddition() throws Exception {
        String result = mathService.calculate("add", 5, 9);
        assertEquals("5 + 9 = 14", result);
    }

    @Test
    public void testCalculateHandlesSubtraction() throws Exception {
        String result = mathService.calculate("subtract", 5, 9);
        assertEquals("5 - 9 = -4", result);
    }

    @Test
    public void testCalculateHandlesMultiplication() throws Exception {
        String result = mathService.calculate("multiply", 5, 9);
        assertEquals("5 * 9 = 45", result);
    }

    @Test
    public void testCalculateHandlesDivision() throws Exception {
        String result = mathService.calculate("divide", 9, 3);
        assertEquals("9 / 3 = 3", result);
    }

    @Test
    public void testSumValues() throws Exception {
        String result = mathService.sumValues(new Integer[]{1,2,3,4,5});
        assertEquals("1 + 2 + 3 + 4 + 5 = 15", result);
    }
}