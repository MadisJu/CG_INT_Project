package com.Lennud.proj.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Lennud.proj.dbmodels.Flight;
import com.Lennud.proj.helper.FlightService;

@RestController
public class FlightController
{
    @Autowired
    private FlightService flightService;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) 
    {
        return String.format("Hello %s!", name);
    }
    @GetMapping("/api/flights")
    public List<Flight> Flights(@RequestParam(required = false) String start,
                                @RequestParam(required = false) String destination,
                                @RequestParam(required = false) String date,
                                @RequestParam(required = false) Integer price) 
    {
        return flightService.getFlights();
    }
}