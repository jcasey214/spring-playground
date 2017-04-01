package com.example.service;

import com.example.model.AreaRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
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

    public String calculateArea(AreaRequest request) {
        DecimalFormat numberFormat = new DecimalFormat("#.00000");
        switch(request.getType()) {
            case "circle":
                if(request.getRadius() == null){
                    return "Invalid";
                }
                return String.format("Area of a circle with a radius of %s is %s",
                        request.getRadius(),
                        numberFormat.format(Math.PI * Math.pow(request.getRadius(), 2)));
            case "rectangle":
                if(request.getWidth() == null || request.getHeight() == null) {
                    return "Invalid";
                }
                return String.format("Area of a %sx%s rectangle is %s",
                        request.getWidth(),
                        request.getHeight(),
                        request.getWidth() * request.getHeight());
            default:
                return "Invalid";
        }
    }
}
