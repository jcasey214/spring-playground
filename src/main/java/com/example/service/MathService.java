package com.example.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MathService {

    public String calculate(String operation, int x, int y) {
        switch (operation) {
            case "add":
                return String.format("%s + %s = %s", x, y, x + y);
            case "subtract":
                return String.format("%s - %s = %s", x, y, x - y);
            case "multiply":
                return String.format("%s * %s = %s", x, y, x * y);
            case "divide":
                return String.format("%s / %s = %s", x, y, x / y);
            default:
                return "That's not a valid operation man";
        }
    }

    public String sumValues(Integer[] values) {
        Integer sum = Arrays.asList(values).stream().reduce(0, (x, y) -> x + y);
        List<String> valueStrings = Arrays.asList(values).stream().map(val -> val.toString()).collect(Collectors.toList());
        String result = StringUtils.join(valueStrings, " + ");

        return String.format("%s = %s", result, sum);
    }
}
