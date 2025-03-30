package com.Lennud.proj.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Lennud.proj.dblogic.SeatDAO;
import com.Lennud.proj.dbmodels.Seating;

@Service
public class SeatService implements SeatServiceInterface 
{
    @Autowired
    private SeatDAO seatDAO;

    @Override
    public List<Seating> getSeating(long flightID) 
    {
        return seatDAO.findByFlightID(flightID);
    }
    
}
