package com.example;

import com.example.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
