package com.example.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WordCounterTest {
    @Autowired
    private WordCounter wordCounter;

    @Test
    public void testCount() throws Exception {
        String sentence = "How now, brown cow cow";
        Map<String, Integer> count = wordCounter.count(sentence);

        assertThat(count.get("brown"), equalTo(1));
        assertThat(count.get("now"), equalTo(1));
        assertThat(count.get("how"), equalTo(1));
        assertThat(count.get("cow"), equalTo(2));

    }

}