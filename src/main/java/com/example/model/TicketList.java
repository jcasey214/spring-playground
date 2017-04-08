package com.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TicketList {

    @JsonProperty
    private List<Ticket> tickets;

    @JsonCreator
    public TicketList(@JsonProperty("tickets") List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
}
