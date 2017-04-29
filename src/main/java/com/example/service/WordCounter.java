package com.example.service;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WordCounter {
    public Map<String, Integer> count(String str) {

        String s = str.replaceAll("[^a-zA-Z\\s]", "").toLowerCase();

        String[] words = StringUtils.split(s, " ");
        System.out.printf("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.printf(words.toString());
        Map<String, Integer> count = new HashMap<>();

        for(String w : words){
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
