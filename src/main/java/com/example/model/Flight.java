package com.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
}
