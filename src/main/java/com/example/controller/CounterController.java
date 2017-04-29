package com.example.controller;

import com.example.service.WordCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CounterController {

    @Autowired
    private WordCounter wordCounter;

    @PostMapping(value = "/words/count", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Integer> countWords(@RequestBody String sentence) {
        return wordCounter.count(sentence);
    }

}
