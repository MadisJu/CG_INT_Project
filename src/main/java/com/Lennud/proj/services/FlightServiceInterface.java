package com.Lennud.proj.services;

import com.Lennud.proj.dbmodels.Flight;

import java.util.List;

public interface FlightServiceInterface 
{

    //Hangi kõik lennud
    List<Flight> getFlights();

    List<Flight> getFlights(String start, String destination, String date, Float price, int passengers);

    Flight getFlight(Long ID);
    
}