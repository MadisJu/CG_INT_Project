package com.Lennud.proj.dblogic;

import com.Lennud.proj.dbmodels.Seating;
import com.Lennud.proj.dbmodels.CompositeKeys.SeatingID;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface SeatDAO extends CrudRepository<Seating, SeatingID> 
{
    @Query("SELECT s FROM SEATING s WHERE s.flight.id = :flightID")
    List<Seating> findByFlightID(@Param("flightID") Long flightID);
}
