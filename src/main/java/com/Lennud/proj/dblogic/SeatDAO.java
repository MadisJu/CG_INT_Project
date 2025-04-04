package com.Lennud.proj.dblogic;

import com.Lennud.proj.dbmodels.Seating;
import com.Lennud.proj.dbmodels.CompositeKeys.SeatingID;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface SeatDAO extends CrudRepository<Seating, SeatingID> 
{
    @Query("SELECT s FROM Seating s WHERE s.flight.ID = :flightID")
    List<Seating> findByFlightID(@Param("flightID") Long flightID);

    @Query("SELECT COUNT(*) FROM Seating s WHERE s.flight.ID = :flightID")
    int findFreeSeatsByFlightID(@Param("flightID") Long flightID);

    @Modifying
    @Transactional
    @Query("UPDATE Seating s SET s.taken = true WHERE s.flight.id = :flightID AND s.seat IN :seatNumbers")
    void markSeatsAsTaken(@Param("flightID") Long flightID, @Param("seatNumbers") List<Integer> seatNumbers);
}
