package com.Lennud.proj.dblogic;

import com.Lennud.proj.dbmodels.Flight;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

// Interface CRUD-i jaoks
public interface FlightDAO extends CrudRepository<Flight, Long> 
{
    /*
     * Query filtriteeritud andmete jaoks. Kui on filtrit ei ole antud siis ta ei kontrolli seda filtrit.
     */
    @Query("SELECT f FROM Flight f WHERE (:start IS NULL OR f.algkoht LIKE %:start%) AND (:destination IS NULL OR f.sihtkoht LIKE %:destination%) AND (:date IS NULL OR f.kuupäev LIKE %:date%) AND (:priceMax IS NULL OR f.hind <= :priceMax)")
    List<Flight> findByFilters(@Param("start") String start, 
                        @Param("destination") String destination, 
                        @Param("date") String date, 
                        @Param("priceMax") Float priceMax);

}