package com.example.controller;

import com.example.model.Flight;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @GetMapping("/flight")
    public Flight getFlight() {
        return new Flight(new GregorianCalendar(2017, 3, 21, 8, 34).getTime(),
                asList(new Flight.Ticket(new Flight.Ticket.Passenger("Some name", "Some other name"), 200)));
    }
}
