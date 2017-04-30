package com.example.model.remote;

import com.example.model.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class OmdbResponse {
    @JsonProperty("Search")
    private List<Movie> search;
    private String totalResults;
    @JsonProperty("Response")
    private String response;
}
