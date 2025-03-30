package com.Lennud.proj.dbmodels;

import com.Lennud.proj.dbmodels.CompositeKeys.SeatingID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@NoArgsConstructor
@Builder

/*
 * Andmed Istmete jaoks, kus siis tableis on ühe lennuki jaoks x arv istet, ja igale istmele on oma istmetüüp.
 * 
 * Composite primary key jaoks idee võetud siit
 * https://www.baeldung.com/jpa-composite-primary-keys
 * 
 * seatType : 1 - Tavaline, 2 - Aknaiste, 3 - 1. klassi iste.
 */
@AllArgsConstructor
@IdClass(SeatingID.class)
public class Seating 
{
    @Id
    @ManyToOne
    @JoinColumn(name = "flightID", referencedColumnName = "ID", nullable = false)
    @MapsId("flight")
    @JsonIgnore
    private Flight flight;
    @Id
    private int seat;
    private int seatType;
    private boolean taken;
}
