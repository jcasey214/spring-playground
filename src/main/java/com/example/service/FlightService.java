package com.example.service;

import com.example.model.Ticket;
import com.example.model.TicketList;
import org.springframework.stereotype.Service;

@Service
public class FlightService {
    public Integer calculateTotal(TicketList ticketList) {
        Integer sum =  ticketList.getTickets().stream()
                .map(ticket -> ticket.getPrice())
                .mapToInt(i -> i)
                .sum();

        return sum;
    }
}
