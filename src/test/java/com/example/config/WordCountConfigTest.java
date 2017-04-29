package com.example.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {
        "wordCount.caseSensitive=true",
        "wordCount.words.skip[0]=do",
        "wordCount.words.skip[1]=re",
        "wordCount.words.skip[2]=mi"
})
public class WordCountConfigTest {

    @Autowired
    private WordCountConfig config;

    @Test
    public void testPropertiesAreMappedCorrectly() {
        assertThat(config.getCaseSensitive(), equalTo(true));
        assertTrue(config.getWords().getSkip().contains("do"));
        assertTrue(config.getWords().getSkip().contains("re"));
        assertTrue(config.getWords().getSkip().contains("mi"));
    }
}