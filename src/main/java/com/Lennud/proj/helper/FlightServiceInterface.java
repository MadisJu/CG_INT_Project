package com.Lennud.proj.helper;

import com.Lennud.proj.dbmodels.Flight;

import java.util.List;

public interface FlightServiceInterface 
{

    //Hangi kõik lennud
    List<Flight> getFlights();
    
}