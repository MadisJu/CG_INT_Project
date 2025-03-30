package com.Lennud.proj.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Lennud.proj.services.SeatService;
import com.Lennud.proj.dbmodels.Seating;


@CrossOrigin(origins = "*", maxAge = 3600)
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
}
