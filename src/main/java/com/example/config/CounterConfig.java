package com.example.config;

import com.example.service.WordCounter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CounterConfig {

    @Bean
    public WordCounter getCounter() {
        return new WordCounter();
    }
}
