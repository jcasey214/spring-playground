package com.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Movie {
    private String title;
    private String imdbId;
    private String poster;
    private Integer year;
    private String type;

    @JsonCreator

    public Movie(@JsonProperty("Title") String title,
                 @JsonProperty("imdbID") String imdbId,
                 @JsonProperty("Poster") String poster,
                 @JsonProperty("Year") Integer year,
                 @JsonProperty("Type") String type) {
        this.title = title;
        this.imdbId = imdbId;
        this.poster = poster;
        this.year = year;
        this.type = type;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("imdbId")
    public String getImdbId() {
        return imdbId;
    }

    @JsonProperty("poster")
    public String getPoster() {
        return poster;
    }

    @JsonProperty("year")
    public Integer getYear() {
        return year;
    }

    @JsonIgnore
    public String getType() {
        return type;
    }
}
