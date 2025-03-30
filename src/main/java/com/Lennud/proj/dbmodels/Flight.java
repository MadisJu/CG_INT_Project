package com.Lennud.proj.dbmodels;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

/*
 * Lennu object andmete salvestamiseks andmebaasi.
 */
public class Flight 
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    private String algkoht;
    private String sihtkoht;
    private String kuupäev;
    private double lennuaeg;
    private int lahkumisetund;
    private int lahkumiseminutid;
    private float hind;

    public Flight(String start, String end, String date, double time, int hour, int minute, float price) 
    {
        this.algkoht = start;
        this.sihtkoht = end;
        this.kuupäev = date;
        this.lennuaeg = time;
        this.lahkumisetund = hour;
        this.lahkumiseminutid = minute;
        this.hind = price;
    }
}
