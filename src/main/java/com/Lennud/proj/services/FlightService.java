package com.Lennud.proj.services;

import java.util.List;
import java.util.stream.Collectors;

import com.Lennud.proj.dbmodels.Flight;
import com.Lennud.proj.dblogic.FlightDAO;
import com.Lennud.proj.dblogic.SeatDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class FlightService implements FlightServiceInterface 
{
    //Muutuja repository jaoks, mis injekteeritakse automaatselt.
    @Autowired
    private FlightDAO flightDAO;
    @Autowired
    private SeatDAO seatDAO;

    
    /**
     * Funktsioon lendude tagastamiseks listina andmebaasist.
     * @return List<Flight>
     */
    @Override
    public List<Flight> getFlights() 
    {
       return (List <Flight>) flightDAO.findAll();
    }


    @Override
    public List<Flight> getFlights(String start, String destination, String date, Float price, int passengers) 
    {
        List<Flight> data = flightDAO.findByFilters(start, destination, date, price);
        
        return data.stream().filter(flight -> seatDAO.findFreeSeatsByFlightID(flight.getId()) >= passengers).collect(Collectors.toList());
    }


    @Override
    public Flight getFlight(Long ID) 
    {
        return (Flight) flightDAO.findById(ID).get();
    }

}
