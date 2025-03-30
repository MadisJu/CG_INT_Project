package com.Lennud.proj.services;

import com.Lennud.proj.dbmodels.Seating;

import java.util.List;


public interface SeatServiceInterface 
{
    

    List<Seating> getSeating(long flightID);
    
}
