package com.Lennud.proj.services;

import com.Lennud.proj.dbmodels.Seating;

import java.util.List;


public interface SeatServiceInterface 
{
    

    List<Seating> getSeating(long flightID);

    List<Integer> getSuggestedSeats(long flightID, int passengers);

    void markSeatsAsTaken(long flightID, List<Integer> seats);
    
}
