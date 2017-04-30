package com.example.controller;

import com.example.model.Movie;
import com.example.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("/movies")
    public List<Movie> getMovies(@RequestParam("q") String q) {
        System.out.println("hit the controller with query" + q);
        return movieService.fetchMovies(q);
    }
}
