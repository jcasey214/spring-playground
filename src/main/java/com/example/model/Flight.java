package com.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class Flight {

    @JsonProperty("Departs")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private final Date departs;

    @JsonProperty("Tickets")
    private final List<Ticket> tickets;

    public Flight(Date departs, List<Ticket> tickets) {
        this.departs = departs;
        this.tickets = tickets;
    }


    public static class Ticket {

        @JsonProperty("Passenger")
        private final Passenger passenger;

        @JsonProperty("Price")
        private final Integer price;

        public Ticket(Passenger passenger, Integer price) {
            this.passenger = passenger;
            this.price = price;
        }
        

        public static class Passenger {

            @JsonProperty("FirstName")
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private final String firstName;

            @JsonProperty("LastName")
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private final String lastName;

            public Passenger(String firstName, String lastName) {
                this.firstName = firstName;
                this.lastName = lastName;
            }
        }
    }
}
