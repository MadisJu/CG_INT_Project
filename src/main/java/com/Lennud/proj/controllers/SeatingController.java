package com.Lennud.proj.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Lennud.proj.services.SeatService;
import com.Lennud.proj.dbmodels.Seating;


@CrossOrigin(origins = "*", allowedHeaders = "*",maxAge = 3600)
@RestController
public class SeatingController 
{
    @Autowired
    private SeatService seatService;

    @GetMapping("/api/seats")
    public List<Seating> Seats(@RequestParam(required = true, defaultValue = "0") Long ID)
    {
        if(ID == 0) return null;

        return seatService.getSeating(ID);
    }

    @GetMapping("/api/suggestedseats")
    public List<Integer> getMethodName( @RequestParam(required = true, defaultValue = "0") Long ID,
                                        @RequestParam(required = true, defaultValue = "1") Integer passengers) 
    {
        return seatService.getSuggestedSeats(ID, passengers);
    }

    @PostMapping("/api/updateseats")
    public ResponseEntity<String> updateSeats(@RequestBody Map<String, Object> requestBody) 
    {
        Long flightID = Long.valueOf((Integer) requestBody.get("ID"));
        List<Integer> seatNumbers = (List<Integer>) requestBody.get("selectedSeats");

    
        seatService.markSeatsAsTaken(flightID, seatNumbers);
    
        return ResponseEntity.ok("Message:Seats booked succesfully");
    }
    
}
