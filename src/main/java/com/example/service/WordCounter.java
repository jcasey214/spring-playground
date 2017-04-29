package com.example.service;

import com.example.config.WordCountConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class WordCounter {

    @Autowired
    WordCountConfig config;

    public Map<String, Integer> count(String str) {
        String s;

        if (config.getCaseSensitive()) {
            s = str.replace("[^a-zA-Z\\s]", "");
        } else {
            s = str.replaceAll("[^a-zA-Z\\s]", "").toLowerCase();
        }

        if(config.getWords().getSkip().size() > 0){
            for (String w : config.getWords().getSkip()){
                s = s.replaceAll(w, "");
            }
        }

        String[] words = StringUtils.split(s, " ");
        Map<String, Integer> count = new HashMap<>();

        for (String w : words) {
            if (count.containsKey(w)) {
                Integer num = count.get(w);
                count.put(w, num + 1);
            } else {
                count.put(w, 1);
            }
        }
        return count;
    }
}
