package com.example.controller;

import com.example.model.Passenger;
import com.example.model.Ticket;
import com.example.model.TicketList;
import com.example.service.FlightService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({FlightController.class, FlightService.class})
@AutoConfigureMockMvc(secure=false)
public class FlightControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetFlight() throws Exception {
        this.mockMvc.perform(
                get("/flights/flight")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$.Tickets[0].Passenger.FirstName", is("Some name")))
                .andExpect(jsonPath("$.Tickets[0].Passenger.LastName", is("Some other name")))
                .andExpect(jsonPath("$.Tickets[0].Price", is(200)));
    }

    @Test
    public void testGetFlights() throws Exception {
        this.mockMvc.perform(
                get("/flights")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].Departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$[0].Tickets[0].Passenger.FirstName", is("Some name")))
                .andExpect(jsonPath("$[0].Tickets[0].Passenger.LastName", is("Some other name")))
                .andExpect(jsonPath("$[0].Tickets[0].Price", is(200)))
                .andExpect(jsonPath("$[1].Departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$[1].Tickets[0].Passenger.FirstName", is("Some other name")))
                .andExpect(jsonPath("$[1].Tickets[0].Passenger.LastName").doesNotExist())
                .andExpect(jsonPath("$[1].Tickets[0].Price", is(400)));
    }

    @Test
    public void testPostTicketTotal() throws Exception {
        List<Ticket> tickets = asList(new Ticket(new Passenger("some name", "Some other name"), 200),
                new Ticket(new Passenger("Name B", "Name C"), 150));
        TicketList ticketList = new TicketList(tickets);
        Gson builder = new GsonBuilder().create();
        String jsonString = builder.toJson(ticketList);

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonString);

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(350)));
    }

    @Test
    public void testPostTicketTotal_sendStringLiteralJson() throws Exception {
        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"tickets\": [{\"passenger\": {\"firstName\": \"Jon\", \"lastName\": \"casey\"}, \"price\": 500}]}");

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(500)));
    }

    @Test
    public void testPostTicketTotal_serializeJsonWithGson() throws Exception {
        JsonObject wrapper = new JsonObject();
        JsonArray ticketList = new JsonArray();
        JsonObject ticket = new JsonObject();
        JsonObject passenger = new JsonObject();

        passenger.addProperty("firstName", "Mike");
        passenger.addProperty("lastName", "Hawk");

        ticket.add("passenger", passenger);
        ticket.addProperty("price", 799);

        ticketList.add(ticket);

        wrapper.add("tickets", ticketList);

        Gson builder = new GsonBuilder().create();

        String jsonString = builder.toJson(wrapper);

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonString);

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(799)));
    }
}