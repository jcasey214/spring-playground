package com.example;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/")
    public String helloWorld() {
        return "Hello from Spring!";
    }

    @PostMapping(name = "/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String goodbyeWorld() {
        return "Goodbye from Spring!";
    }
}
