package com.example.controller;

import com.example.model.Flight;
import com.example.model.Passenger;
import com.example.model.Ticket;
import com.example.model.TicketList;
import com.example.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

@RestController
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/flights/flight")
    public Flight getFlight() {
        return new Flight(new GregorianCalendar(2017, 3, 21, 8, 34).getTime(),
                asList(new Ticket(new Passenger("Some name", "Some other name"), 200)));
    }

    @GetMapping("/flights")
    public List<Flight> getFlights() {
        return asList(new Flight(new GregorianCalendar(2017, 3, 21, 8, 34).getTime(),
                asList(new Ticket(new Passenger("Some name", "Some other name"), 200))),
                new Flight(new GregorianCalendar(2017, 3, 21, 8, 34).getTime(),
                asList(new Ticket(new Passenger("Some other name", null), 400))));
    }

    @PostMapping("/flights/tickets/total")
    public Map<String, Integer> getTicketTotal(@RequestBody TicketList ticketList) {
        Integer total = flightService.calculateTotal(ticketList);
        Map<String, Integer> result = new HashMap<>();
        result.put("result", total);
        return result;
    }
}
