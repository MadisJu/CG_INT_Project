package com.Lennud.proj.helper;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.Lennud.proj.dblogic.FlightDAO;
import com.Lennud.proj.dbmodels.Flight;

@Configuration
public class DataLoader 
{
    private final FlightDAO access;

    public DataLoader(FlightDAO scaffold)
    {
        this.access = scaffold;
    }

    @Bean
    public ApplicationRunner loadTestData() {
        return args -> 
        {
            access.save(new Flight("TLN", "PDB", "2025-03-30", 2.5, 15, 15, 250f));
            access.save(new Flight("TLN", "RIX", "2025-03-29", 3.5, 19, 15, 150f));
        };
    }
}
