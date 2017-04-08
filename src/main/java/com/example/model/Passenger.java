package com.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Passenger {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String firstName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String lastName;

    @JsonCreator
    public Passenger(@JsonProperty("firstName") String firstName,
                     @JsonProperty("lastName") String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @JsonProperty("FirstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("LastName")
    public String getLastName() {
        return lastName;
    }
}
