package com.example;

import com.example.model.AreaRequest;
import com.example.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/math")
public class MathController {

    @Autowired
    MathService mathService;

    @GetMapping("/pi")
    public String getPi() {
        return "3.141592653589793";
    }

    @GetMapping("/calculate")
    public String calculate(@RequestParam("operation") String operation, @RequestParam("x") int x, @RequestParam("y") int y) {
        return mathService.calculate(operation, x, y);
    }

    @PostMapping("/sum")
    public String getSumOfValues(@RequestParam("n") Integer[] values) {
        return mathService.sumValues(values);
    }

    @RequestMapping(value = "/volume/{length}/{width}/{height}")
    public String getVolume(@PathVariable("length") Integer length, @PathVariable("width") Integer width, @PathVariable("height") Integer height) {
        return String.format("The volume of a %sx%sx%s rectangle is %s", length, width, height, length * width * height);
    }

    @PostMapping(value = "/area",  consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String getArea(AreaRequest request) {
        return mathService.calculateArea(request);
    }
}
