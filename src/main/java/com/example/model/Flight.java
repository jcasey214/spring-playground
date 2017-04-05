package com.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class Flight {

    @JsonProperty
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private final Date departs;
    @JsonProperty
    private final List<Ticket> tickets;

    public Flight(Date departs, List<Ticket> tickets) {
        this.departs = departs;
        this.tickets = tickets;
    }

    public static class Ticket {

        @JsonProperty
        private final Passenger passenger;
        @JsonProperty
        private final Integer price;

        public Ticket(Passenger passenger, Integer price) {
            this.passenger = passenger;
            this.price = price;
        }

        public static class Passenger {
            @JsonProperty
            private final String firstName;
            @JsonProperty
            private final String lastName;

            public Passenger(String firstName, String lastName) {
                this.firstName = firstName;
                this.lastName = lastName;
            }
        }
    }
}
