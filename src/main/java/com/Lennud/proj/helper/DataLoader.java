package com.Lennud.proj.helper;

import java.util.Random;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.Lennud.proj.dblogic.FlightDAO;
import com.Lennud.proj.dblogic.SeatDAO;
import com.Lennud.proj.dbmodels.Flight;
import com.Lennud.proj.dbmodels.Seating;

@Configuration
public class DataLoader 
{
    private final FlightDAO flight_access;
    private final SeatDAO seat_access;
    private Random random_gen;

    public DataLoader(FlightDAO scaffold, SeatDAO seat_scaffold)
    {
        this.flight_access = scaffold;
        this.seat_access = seat_scaffold;
        this.random_gen = new Random();
    }

    public void SaveData(Flight temp)
    {
        flight_access.save(temp);

        for (int i = 1; i <= 40; i++) 
        {
            int type = (i%4 == 1 || i%10 == 0) ? 2 : 1;
            if(i < 21) type = 3;
            boolean taken = random_gen.nextInt(2) == 1 ? true : false;
            seat_access.save(new Seating(temp, i, type, taken));
        }
    }

    @Bean
    public ApplicationRunner loadTestData() {
        return args -> 
        {
            Flight temp = new Flight("TLN", "PDB", "2025-03-30", 2.5, 15, 15, 250f);
            SaveData(temp);

            temp = new Flight("TLN", "RIX", "2025-03-29", 3.5, 19, 15, 150f);
            SaveData(temp);
        };
    }
}
