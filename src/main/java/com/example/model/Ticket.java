package com.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Ticket {
    private final Passenger passenger;

    private final Integer price;

    @JsonCreator
    public Ticket(@JsonProperty("passenger")Passenger passenger,
                  @JsonProperty("price") Integer price) {
        this.passenger = passenger;
        this.price = price;
    }

    @JsonProperty("Passenger")
    public Passenger getPassenger() {
        return passenger;
    }

    @JsonProperty("Price")
    public Integer getPrice() {
        return price;
    }
}
