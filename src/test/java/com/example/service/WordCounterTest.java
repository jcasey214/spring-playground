package com.example.service;

import com.example.config.WordCountConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WordCounterTest {
    @Autowired
    private WordCounter wordCounter;

    @MockBean
    WordCountConfig config;

    @Before
    public void setup() {
        WordCountConfig.Words words = new WordCountConfig.Words();
        words.setSkip(Collections.emptyList());
        when(config.getCaseSensitive()).thenReturn(false);
        when(config.getWords()).thenReturn(words);
    }

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