package com.example.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CounterControllerTest {

    @Autowired
    CounterController counterController;

    @Test
    public void testCountWords() throws Exception {
        String sentence = "How now, brown cow cow";
        Map<String, Integer> count = counterController.countWords(sentence);

        assertThat(count.get("brown"), equalTo(1));
        assertThat(count.get("now"), equalTo(1));
        assertThat(count.get("how"), equalTo(1));
        assertThat(count.get("cow"), equalTo(2));

    }
}