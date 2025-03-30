package com.Lennud.proj.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<Integer> getSuggestedSeats(long flightID, int passengers) 
    {
        List<Seating> seats = seatDAO.findByFlightID(flightID);
        List<Integer> suggestedSeats = new ArrayList<>();
        
        /*
         * Puhasta võetud istmekohad
         */
        seats = seats.stream().filter(seat -> !seat.isTaken()).toList();

        /*
         * Idee on nagu heatmap?
         * Vaatame igasugused omadused läbi, kas istmete kõrval on vabu istekohti
         * Kas iste on akna juures, jne.
         */
        HashMap<Integer, Integer> seatValues = new HashMap<>();
        for (int i = 0; i < seats.size(); i++) 
        {
            seatValues.put(seats.get(i).getSeat(), 0);
        }

        for (int i = 0; i < seats.size(); i++) 
        {
            int seat = seats.get(i).getSeat();
            int row = (int) seat/6;
            //Kas istmed on akna all +10, +6 kui lähedal läbikäigule
            if(seat%6 == 0 || seat%6 == 1) seatValues.put(seat, seatValues.get(seat) + 30);
            if(seat%6 == 2 || seat%6 == 3) seatValues.put(seat, seatValues.get(seat) + 16);
            //Kaugus väljapääsust
            seatValues.put(seat, seatValues.get(seat) + 10 - row);

            if(seat%6 != 0)
            {
                if (seatValues.containsKey(seat + 1)) { // Left neighbor
                    seatValues.put(seat, seatValues.get(seat) + 8);
                }
            }
            if(seat%6 != 1)
            {
                if (seatValues.containsKey(seat - 1)) { // Right neighbor
                    seatValues.put(seat, seatValues.get(seat) + 8);
                }
            }

            for (int j = 1; j <= 6; j++) 
            {
                //Mitu vaba istet on reas.
                if(seatValues.containsKey(row * 6 + j)) seatValues.put(seat, seatValues.get(seat) + 4);
            }
            System.out.println(seat + " with value of : " + seatValues.get(seat));
        }



        //Valitud istme kõrval olevad vabad istmed on väärtuslikumad.
        for (int i = 0; i < passengers; i++) 
        {
            
            int mostValuableSeat = seatValues.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);

            System.out.println(i + " passenger with value of : " + mostValuableSeat + " - " + seatValues.get(mostValuableSeat));
            if(mostValuableSeat%6 != 1)
            {
                if(seatValues.containsKey(mostValuableSeat - 1))
                    seatValues.put(mostValuableSeat-1, seatValues.get(mostValuableSeat-1) + 12);
            }
            if(mostValuableSeat%6 != 0)
            {
                if(seatValues.containsKey(mostValuableSeat + 1))
                    seatValues.put(mostValuableSeat+1, seatValues.get(mostValuableSeat+1) + 12);
            }
            
            suggestedSeats.add(mostValuableSeat);
            seatValues.remove(mostValuableSeat);
        }

        return suggestedSeats;
    }
    
}
