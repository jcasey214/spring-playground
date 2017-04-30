package com.example.service;

import com.example.model.Movie;
import com.example.model.remote.OmdbResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MovieService {
    private final RestTemplate restTemplate = new RestTemplate();

    public List<Movie> fetchMovies(String q) {
        String url = String.format("http://www.omdbapi.com/?s=%s", q);
        OmdbResponse queryResponse = this.restTemplate.getForObject(url, OmdbResponse.class);

        return queryResponse.getSearch();

    }
}
