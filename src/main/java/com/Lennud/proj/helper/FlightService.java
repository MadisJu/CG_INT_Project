package com.Lennud.proj.helper;

import java.util.List;

import com.Lennud.proj.dbmodels.Flight;
import com.Lennud.proj.dblogic.FlightDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class FlightService implements FlightServiceInterface 
{
    //Muutuja repository jaoks, mis injekteeritakse automaatselt.
    @Autowired
    private FlightDAO flightDAO;

    
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
    public List<Flight> getFlights(String start, String destination, String date, Float price) 
    {
        System.out.println(destination);
        return (List<Flight>) flightDAO.findByFilters(start, destination, date, price);
    }


    @Override
    public Flight getFlight(Long ID) 
    {
        return (Flight) flightDAO.findById(ID).get();
    }

}
