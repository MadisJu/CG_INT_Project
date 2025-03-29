package com.Lennud.proj.dblogic;

import com.Lennud.proj.dbmodels.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

// Interface CRUD-i jaoks
public interface FlightDAO extends CrudRepository<Flight, Long> 
{

}